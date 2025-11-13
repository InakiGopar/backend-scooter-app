package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterRequestPatchDTO(
        Long scooterId,
        ScooterState state
) {
}
