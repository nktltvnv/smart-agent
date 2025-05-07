package io.github.nktltvnv.smartagent.web;

import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatModel chatModel;

    @PostMapping
    public ResponseEntity<String> chat() {
        var response = chatModel.call("Hi, how are you?");
        return ResponseEntity.ok(response);
    }
}
