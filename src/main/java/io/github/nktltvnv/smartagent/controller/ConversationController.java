package io.github.nktltvnv.smartagent.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nktltvnv.smartagent.service.ConversationService;

@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    public Flux<String> stream(@RequestBody final String message, @AuthenticationPrincipal final Principal principal) {
        return conversationService.stream(message, principal);
    }
}
