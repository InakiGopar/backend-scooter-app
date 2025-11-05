package ar.edu.unicen.tripservice.domain.dtos.request.trip;

import java.util.Date;

public record TripRequestUpdateDTO(
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
