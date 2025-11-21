package ar.edu.unicen.chatbotservice.domain.dtos.response;

public record Message(
        String role,
        String content
) {
}
