package ar.edu.unicen.userservice.domain.dtos.response;

public record UserScooterUsageResponseDTO(
        String name,
        String lastName,
        int totalUsages
) {
}
