package ar.edu.unicen.userservice.domain.dtos.response;

public record UserResponseDTO(
        Long userId,
        String name,
        String lastName,
        String email,
        int phone
) {}
