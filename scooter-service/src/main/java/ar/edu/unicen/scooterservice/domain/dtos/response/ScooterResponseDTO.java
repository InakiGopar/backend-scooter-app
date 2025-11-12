package ar.edu.unicen.scooterservice.domain.dtos.response;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.entities.ScooterState;

public record ScooterResponseDTO(
        Long scooterId,
        float latitude,
        float longitude,
        ScooterState state
) {
    public static ScooterResponseDTO toDTO(Scooter scooter) {
        return new ScooterResponseDTO(
                scooter.getScooterId(),
                scooter.getLatitude(),
                scooter.getLongitude(),
                scooter.getState()
        );
    }
}
