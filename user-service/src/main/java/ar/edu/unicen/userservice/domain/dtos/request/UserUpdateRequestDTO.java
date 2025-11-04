package ar.edu.unicen.userservice.domain.dtos.request;

import ar.edu.unicen.userservice.domain.entities.Role;

public record UserUpdateRequestDTO (
    Long userId,
    String name,
    String lastName,
    String email,
    int phone
) {}
