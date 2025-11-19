package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.entities.ScooterState;
import ar.edu.unicen.scooterservice.domain.entities.Stop;

public record ScooterRequestDTO(
         double latitude,
         double longitude,
         ScooterState state,
         Long currentStopId
) {
    public Scooter toEntity(Stop stop) {
        return new Scooter(
                latitude, longitude, state, stop
        );
    }
}
