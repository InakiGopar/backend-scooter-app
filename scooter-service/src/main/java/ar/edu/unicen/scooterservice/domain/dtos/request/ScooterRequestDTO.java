package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterRequestDTO(
         float latitude,
         float longitude,
         ScooterState state
) {
    public Scooter toEntity() {
        return new Scooter(
                latitude, longitude, state
        );
    }
}
