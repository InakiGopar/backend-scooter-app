package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

import java.time.Instant;
import java.util.UUID;

public record TripResponseDTO(
        UUID tripId,
        Long userId,
        Long scooterId,
        Long stopSartId,
        Long stopEndId,
        Instant startTime,
        Instant endTime,
        Long kmTraveled,
        Instant startPause,
        Instant endPause,
        float totalPrice,
        String feeId
) {

    public static  TripResponseDTO toDTO(Trip trip) {
        return new TripResponseDTO(
                trip.getTripId(),
                trip.getUserId(),
                trip.getScooterId(),
                trip.getStopStartId(),
                trip.getStopEndId(),
                trip.getStartTime(),
                trip.getEndTime(),
                trip.getKmTraveled(),
                trip.getStartPause(),
                trip.getEndPause(),
                trip.getTotalPrice(),
                trip.getFeeId()
        );
    }
}
