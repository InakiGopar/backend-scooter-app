package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterFinishedTripRequestDTO(
        Long scooterId,
        float latitude,
        float longitude,
        ScooterState state,
        Long endStopId,
        float kilometers
) {
}
