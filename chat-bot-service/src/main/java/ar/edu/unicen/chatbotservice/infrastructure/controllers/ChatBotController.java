package ar.edu.unicen.chatbotservice.infrastructure.controllers;

import ar.edu.unicen.chatbotservice.application.service.ChatBotService;
import ar.edu.unicen.chatbotservice.domain.dtos.request.PromptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody PromptDTO request) {
        String response = chatBotService.processPrompt(request.message());
        return ResponseEntity.ok(response);
    }

}
