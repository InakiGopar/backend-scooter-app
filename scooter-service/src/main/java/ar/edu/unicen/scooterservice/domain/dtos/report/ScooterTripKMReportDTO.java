package ar.edu.unicen.scooterservice.domain.dtos.report;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.model.Trip;

public record ScooterTripKMReportDTO(
        Long scooterId,
        float kilometers,
        int pause
) {
    public static ScooterTripKMReportDTO toDTO(Scooter scooter, Trip trip){
        return new ScooterTripKMReportDTO(
                scooter.getScooterId(),
                scooter.getKilometers(),
                trip.getPause()
        );
    }

}
