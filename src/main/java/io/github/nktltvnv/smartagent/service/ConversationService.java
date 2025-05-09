package io.github.nktltvnv.smartagent.service;

import java.security.Principal;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;

import io.github.nktltvnv.smartagent.util.constant.SecurityConstant;
import io.github.nktltvnv.smartagent.util.constant.SessionConstant;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    public Flux<String> anonStream(final String message, final WebSession session) {
        var conversationId = session.getAttribute(SessionConstant.SESSION_CONVERSATION_ID_KEY);
        if (conversationId == null) {
            conversationId = UUID.randomUUID().toString();
            session.getAttributes().put(SessionConstant.SESSION_CONVERSATION_ID_KEY, conversationId);
        }
        log.info("Anonymous user initiated a conversation [{}]", conversationId);
        return stream(message, conversationId.toString());
    }

    public Flux<String> stream(final String message, final Principal principal) {
        if (principal instanceof JwtAuthenticationToken jwt) {
            var userId = jwt.getTokenAttributes().get(SecurityConstant.JWT_USER_ID_KEY);
            if (userId == null) {
                throw new IllegalStateException("JWT token does not contain user ID");
            }
            log.info("User [{}] initiated a conversation", userId);
            return stream(message, userId.toString());
        }
        throw new AccessDeniedException("JWT authentication is required");
    }

    public Flux<String> stream(final String message, String conversationId) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory)
                        .conversationId(conversationId)
                        .build())
                .stream()
                .content();
    }
}
