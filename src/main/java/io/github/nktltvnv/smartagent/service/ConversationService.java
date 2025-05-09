package io.github.nktltvnv.smartagent.service;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;

import io.github.nktltvnv.smartagent.util.constant.SessionConstant;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final SessionService sessionService;
    private final SecurityService securityService;

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    public Flux<String> anonStream(final String message, final WebSession session) {
        var conversationId = sessionService.getAttribute(SessionConstant.SESSION_CONVERSATION_ID_KEY, session);
        if (conversationId.isEmpty()) {
            var generatedId = UUID.randomUUID().toString();
            conversationId = Optional.of(generatedId);
            sessionService.setAttribute(SessionConstant.SESSION_CONVERSATION_ID_KEY, generatedId, session);
        }
        return stream(message, conversationId);
    }

    public Flux<String> stream(final String message, final Principal principal) {
        var conversationId = securityService.getCurrentUserId(principal);
        return stream(message, conversationId);
    }

    public Flux<String> stream(final String message, final Optional<String> conversationId) {
        var requestSpec = chatClient.prompt().user(message);
        conversationId.ifPresent(id -> requestSpec.advisors(
                MessageChatMemoryAdvisor.builder(chatMemory).conversationId(id).build()));
        return requestSpec.stream().content();
    }
}
