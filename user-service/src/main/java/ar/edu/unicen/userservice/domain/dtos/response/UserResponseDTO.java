package ar.edu.unicen.userservice.domain.dtos.response;

public record UserResponseDTO(
        String name,
        String lastName,
        String email,
        int phone
) {}
