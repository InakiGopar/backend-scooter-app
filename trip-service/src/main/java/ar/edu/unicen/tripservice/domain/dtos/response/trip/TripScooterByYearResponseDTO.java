package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import java.time.Instant;

public record TripScooterByYearResponseDTO(
        Long ScooterId,
        Instant year,
        Integer tripCount
) {
}
