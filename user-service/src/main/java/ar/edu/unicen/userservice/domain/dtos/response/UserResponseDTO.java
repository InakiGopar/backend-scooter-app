package ar.edu.unicen.userservice.domain.dtos.response;

import ar.edu.unicen.userservice.domain.entities.Role;
import ar.edu.unicen.userservice.domain.entities.User;

public record UserResponseDTO(
        Long userId,
        Role role,
        String name,
        String lastName,
        String email,
        int phone
) {

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getRole(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
