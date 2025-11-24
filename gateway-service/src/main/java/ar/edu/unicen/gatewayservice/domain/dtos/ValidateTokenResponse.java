package ar.edu.unicen.gatewayservice.domain.dtos;

import ar.edu.unicen.gatewayservice.domain.model.Role;

public record ValidateTokenResponse(
        boolean valid,
        String username,
        Role role
) {
}
