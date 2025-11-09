package ar.edu.unicen.tripservice.domain.dtos.request.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;

import java.time.Instant;
import java.util.Date;

public record TripRequestDTO(
        Long userId,
        Long scooterId,
        Long stopStartId,
        Long stopEndId,
        Date date,
        Instant startTime,
        Instant endTime,
        Long kmTraveled,
        Instant startPause,
        Instant endPause,
        int limitPauseMinutes,
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
                startTime,
                endTime,
                kmTraveled,
                startPause,
                endPause,
                limitPauseMinutes,
                totalPrice,
                feeId
        );
    }
}
