package ar.edu.unicen.userservice.domain.dtos.request;

import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.entities.Role;
import ar.edu.unicen.userservice.domain.entities.User;

public record UserUpdateRequestDTO (
    Long userId,
    Role role,
    String name,
    String lastName,
    String email,
    int phone
) {

    public User toEntity() {
        return new User(
                role, name, lastName, email, phone
        );
    }

}
