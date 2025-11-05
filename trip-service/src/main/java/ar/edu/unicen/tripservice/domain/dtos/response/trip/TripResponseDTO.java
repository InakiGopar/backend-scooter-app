package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import java.util.Date;

public record TripResponseDTO(
        Long tripId,
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
}
