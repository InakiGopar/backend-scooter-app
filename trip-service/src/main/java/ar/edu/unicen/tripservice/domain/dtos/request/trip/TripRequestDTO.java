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
        // limitPauseMinutes no va aca
        int limitPauseMinutes,
        float totalPrice,
        int pauseCount,
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
                totalPrice,
                pauseCount,
                feeId
        );
    }
}
