package ar.edu.unicen.userservice.domain.dtos.request;

import ar.edu.unicen.userservice.domain.entities.Role;
import ar.edu.unicen.userservice.domain.entities.User;

public record UserRequestDTO(
    Role role,
    String name,
    String password,
    String lastName,
    String email,
    int phone
) {
    public User toEntity() {
        return new User(
            role, name, password ,lastName, email, phone
        );
    }

}
