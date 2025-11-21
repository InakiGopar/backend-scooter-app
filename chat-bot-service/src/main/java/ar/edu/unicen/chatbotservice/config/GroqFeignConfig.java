package ar.edu.unicen.chatbotservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

public class GroqFeignConfig {
    @Value("${groq.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor bearerAuthInterceptor() {
        return requestTemplate ->
                requestTemplate.header("Authorization", "Bearer " + apiKey);
    }
}
