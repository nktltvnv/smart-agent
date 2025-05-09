package io.github.nktltvnv.smartagent.controller;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import io.github.nktltvnv.smartagent.service.ConversationService;

@RestController
@RequestMapping("/anon/conversation")
@RequiredArgsConstructor
public class AnonConversationController {

    private final ConversationService conversationService;

    @PostMapping
    public Flux<String> stream(@RequestBody final String message, final WebSession session) {
        return conversationService.anonStream(message, session);
    }
}
