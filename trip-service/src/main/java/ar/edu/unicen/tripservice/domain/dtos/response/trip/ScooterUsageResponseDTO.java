package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record ScooterUsageResponseDTO(
        Long scooterId,
        Long totalKilometers,
        Integer totalPausesMinutes
) {
}
