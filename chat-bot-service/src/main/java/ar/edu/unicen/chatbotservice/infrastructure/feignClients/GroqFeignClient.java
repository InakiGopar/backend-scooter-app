package ar.edu.unicen.chatbotservice.infrastructure.feignClients;

import ar.edu.unicen.chatbotservice.config.GroqFeignConfig;
import ar.edu.unicen.chatbotservice.domain.dtos.request.GroqRequestDTO;
import ar.edu.unicen.chatbotservice.domain.dtos.response.GroqResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "groqClient",
        url = "${groq.base-url}",
        configuration = GroqFeignConfig.class
)
public interface GroqFeignClient {

    @PostMapping("/v1/chat/completions")
    GroqResponseDTO sendToGroq(GroqRequestDTO request);
}
