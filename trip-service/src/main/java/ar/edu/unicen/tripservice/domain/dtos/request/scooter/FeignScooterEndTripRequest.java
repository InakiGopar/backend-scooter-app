package ar.edu.unicen.tripservice.domain.dtos.request.scooter;

import ar.edu.unicen.tripservice.domain.model.scooter.ScooterState;

public record FeignScooterEndTripRequest(
        Long scooterId,
        double latitude,
        double longitude,
        ScooterState state,
        Long endStopId,
        float kilometers
) {
}
