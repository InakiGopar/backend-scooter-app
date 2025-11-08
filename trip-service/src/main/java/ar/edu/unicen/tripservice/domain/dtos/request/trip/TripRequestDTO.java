package ar.edu.unicen.tripservice.domain.dtos.request.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

import java.util.Date;

public record TripRequestDTO(
        Long userId,
        Long scooterId,
        Long stopStartId,
        Long stopEndId,
        Date date,
        Date startDate,
        Date endDate,
        Long kmTraveled,
        Date pause,
        float totalPrice,
        String feeId
) {
    public Trip toEntity() {
        return new Trip(
                userId,
                scooterId,
                stopStartId,
                stopEndId,
                date,
                startDate,
                endDate,
                kmTraveled,
                pause,
                totalPrice,
                feeId
        );
    }
}
