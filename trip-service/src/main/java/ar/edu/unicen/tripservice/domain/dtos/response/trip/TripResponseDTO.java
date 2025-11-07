package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

import java.util.Date;
import java.util.UUID;

public record TripResponseDTO(
        UUID tripId,
        Long userId,
        Long scooterId,
        Long stopSartId,
        Long stopEndId,
        Date date,
        Date startTime,
        Date endTime,
        Long kmTraveled,
        Date pause,
        float totalPrice
) {

    public static  TripResponseDTO toDTO(Trip trip) {
        return new TripResponseDTO(
                trip.getTripId(),
                trip.getUserId(),
                trip.getScooterId(),
                trip.getStopSartId(),
                trip.getStopEndId(),
                trip.getDate(),
                trip.getStartTime(),
                trip.getEndTime(),
                trip.getKmTraveled(),
                trip.getPause(),
                trip.getTotalPrice()
        );
    }
}
