package ar.edu.unicen.chatbotservice.application.service;

import ar.edu.unicen.chatbotservice.domain.dtos.request.GroqRequestDTO;
import ar.edu.unicen.chatbotservice.domain.dtos.response.GroqResponseDTO;
import ar.edu.unicen.chatbotservice.infrastructure.feignClients.GroqFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatBotService {
    private final GroqFeignClient groqFeignClient;

    @Value("${groq.model}")
    private String model;

    @Value("${groq.temperature}")
    private Double temperature;

    public String processPrompt(String prompt) {

        //Build the request that the API GROQ is waiting
        GroqRequestDTO request = GroqRequestDTO.fromUserPrompt(prompt, model, temperature);
        //Received the response
        GroqResponseDTO response = groqFeignClient.sendToGroq(request);
        //Return the LLM response
        return response.choices().getFirst().message().content();
    }
}
