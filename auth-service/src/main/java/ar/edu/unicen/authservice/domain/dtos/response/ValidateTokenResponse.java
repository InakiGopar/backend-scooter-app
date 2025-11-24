package ar.edu.unicen.authservice.domain.dtos.response;

public record ValidateTokenResponse(
        boolean valid,
        String email,
        String role
) {
}
