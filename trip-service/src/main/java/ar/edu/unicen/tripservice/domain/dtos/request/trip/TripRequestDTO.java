package ar.edu.unicen.tripservice.domain.dtos.request.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

import java.util.Date;

public record TripRequestDTO(
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
    public Trip toEntity() {
        return new Trip(
                userId,
                scooterId,
                stopSartId,
                stopEndId,
                date,
                startTime,
                endTime,
                kmTraveled,
                pause,
                totalPrice
        );
    }
}
