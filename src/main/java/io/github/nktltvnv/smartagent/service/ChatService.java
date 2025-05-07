package io.github.nktltvnv.smartagent.service;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SecurityService securityService;

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    public Flux<String> stream(final String message) {
        var requestSpec = chatClient.prompt().user(message);
        return stream(requestSpec);
    }

    public Flux<String> stream(final String message, final Principal principal) {
        var requestSpec = chatClient.prompt().user(message);
        securityService
                .getCurrentUserId(principal)
                .ifPresent(userId -> requestSpec.advisors(new MessageChatMemoryAdvisor(
                        chatMemory, userId, AbstractChatMemoryAdvisor.DEFAULT_CHAT_MEMORY_RESPONSE_SIZE)));
        return stream(requestSpec);
    }

    public Flux<String> stream(final ChatClient.ChatClientRequestSpec requestSpec) {
        return requestSpec.stream().content();
    }
}
