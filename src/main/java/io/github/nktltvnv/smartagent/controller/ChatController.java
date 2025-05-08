package io.github.nktltvnv.smartagent.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nktltvnv.smartagent.service.ChatService;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/stream")
    public Flux<String> stream(@RequestBody final String message) {
        return chatService.stream(message);
    }

    @PostMapping("/stream/personalized")
    public Flux<String> stream(@RequestBody final String message, @AuthenticationPrincipal final Principal principal) {
        return chatService.stream(message, principal);
    }
}
