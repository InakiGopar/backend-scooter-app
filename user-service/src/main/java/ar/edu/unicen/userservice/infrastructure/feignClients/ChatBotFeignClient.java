package ar.edu.unicen.userservice.infrastructure.feignClients;


import ar.edu.unicen.userservice.domain.dtos.request.PromptRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.response.LLMResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "chat-bot-service", url = "http://localhost:8090/api/chatbot")
public interface ChatBotFeignClient {

    @PostMapping("/chat")
    LLMResponseDTO chat(@RequestBody PromptRequestDTO request);
}
