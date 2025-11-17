package ar.edu.unicen.scooterservice.domain.dtos.report;

import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.model.Trip;

public record ScooterTripKMReportDTO(
        Long scooterId,
        Long kilometers,
        Integer pause
) {
    public static ScooterTripKMReportDTO toDTO(Trip trip){
        return new ScooterTripKMReportDTO(
                trip.getScooterId(),
                trip.getTotalKilometers(),
                trip.getTotalPausesMinutes()
        );
    }

}
