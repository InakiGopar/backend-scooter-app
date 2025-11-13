package ar.edu.unicen.tripservice.domain.dtos.request.trip;

import ar.edu.unicen.tripservice.domain.entities.Trip;


public record TripRequestDTO(
        Long userId,
        Long scooterId,
        Long stopStartId,
        Long stopEndId,
        Long kmTraveled,
        String feeId
) {
    public Trip toEntity() {
        return new Trip(
                userId,
                scooterId,
                stopStartId,
                stopEndId,
                kmTraveled,
                feeId
        );
    }
}
