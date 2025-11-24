package ar.edu.unicen.authservice.domain.dtos.response;

import ar.edu.unicen.authservice.domain.model.Role;

public record ValidateTokenResponse(
        boolean valid,
        String username,
        Role role
) {
}
