package ar.edu.unicen.chatbotservice.domain.dtos.response;

import java.time.Instant;
import java.util.UUID;

public record TripResponseDTO(
        UUID tripId,
        Long userId,
        Long scooterId,
        Long stopStartId,
        Long stopEndId,
        Instant startTime,
        Instant endTime,
        Long kmTraveled,
        Instant startPause,
        Instant endPause,
        float totalPrice,
        String feeId
) {
}
