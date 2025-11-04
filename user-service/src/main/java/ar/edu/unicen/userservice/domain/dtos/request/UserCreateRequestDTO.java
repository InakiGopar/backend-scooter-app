package ar.edu.unicen.userservice.domain.dtos.request;

public record UserCreateRequestDTO(
    String name,
    String lastName,
    String email,
    int phone
) {}
