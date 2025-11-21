package ar.edu.unicen.chatbotservice.infrastructure.controllers;

import ar.edu.unicen.chatbotservice.service.ChatBotService;
import ar.edu.unicen.chatbotservice.dtos.request.PromptDTO;
import ar.edu.unicen.chatbotservice.dtos.response.LLMResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping("/chat")
    public ResponseEntity<LLMResponseDTO> chat(@RequestBody PromptDTO request) {
        return ResponseEntity.ok(chatBotService.processPrompt(request.message()));
    }

    @PostMapping("/historical-data/{userId}")
    public ResponseEntity<LLMResponseDTO> historicalTripData(@PathVariable Long userId, @RequestBody PromptDTO request) {
        return ResponseEntity.ok(chatBotService.historicalTripData(userId, request));
    }

}
