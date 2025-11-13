package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record ScooterUsageResponseDTO(
        Long scooterId,
        float totalKilometers,
        int totalPausesMinutes,
        int totalTrips
) {
}
