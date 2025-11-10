package ar.edu.unicen.scooterservice.domain.dtos.response;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.model.Trip;

public record ScooterTripKMResponseDTO(
        Long scooterId,
        float kilometers,
        int pause
) {
    public static ScooterTripKMResponseDTO toDTO(Scooter scooter, Trip trip){
        return new ScooterTripKMResponseDTO(
                scooter.getScooterId(),
                scooter.getKilometers(),
                trip.getPause()
        );
    }

}
