package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterRequestPatchStateDTO(
        Long scooterId,
        ScooterState state
) {
}
