package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

public record ScooterUsageResponseDTO(
        Long scooterId,
        float totalKilometers,
        int totalPausesMinutes,
        int totalTrips
) {
}
