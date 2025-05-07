package io.github.nktltvnv.smartagent.service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    public Flux<String> stream(final String message) {
        return chatClient.prompt().user(message).stream().content();
    }
}
