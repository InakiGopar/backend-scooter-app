package ar.edu.unicen.supportservice.domain.dtos.request.feign;

import ar.edu.unicen.supportservice.domain.model.ScooterState;

public record ScooterRequestPatchStateDTO(
        ScooterState state
) {
}
