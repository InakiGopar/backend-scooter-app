package ar.edu.unicen.chatbotservice.dtos.response;

public record LLMResponseDTO(
        String llmResponse
) {
    public static LLMResponseDTO toDTO(String llmResponse) {
        return new LLMResponseDTO(llmResponse);
    }
}
