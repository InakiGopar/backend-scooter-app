package ar.edu.unicen.userservice.domain.dtos.response;

import ar.edu.unicen.userservice.domain.entities.User;

public record AuthUserResponseDTO(
        Long userId,
        String email,
        String role,
        String password
) {
    public static AuthUserResponseDTO toDTO(User user) {
        return new AuthUserResponseDTO(
                user.getUserId(),
                user.getEmail(),
                user.getRole().toString(),
                user.getPassword()
        );
    }
}
