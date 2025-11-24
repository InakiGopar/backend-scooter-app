package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterFinishedTripRequestDTO(
        Long scooterId,
        double latitude,
        double longitude,
        ScooterState state,
        Long endStopId,
        float kilometers
) {
}
