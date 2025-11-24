package ar.edu.unicen.authservice.domain.dtos.response;

public record UserResponseDTO(
        Long userId,
        String email,
        String role,
        String password) {
}
