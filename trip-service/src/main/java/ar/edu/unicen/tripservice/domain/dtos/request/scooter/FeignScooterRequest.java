package ar.edu.unicen.tripservice.domain.dtos.request.scooter;

import ar.edu.unicen.tripservice.domain.model.scooter.ScooterState;

public record FeignScooterRequest(
        Long scooterId,
        ScooterState state
) {
}
