package ar.edu.unicen.userservice.domain.dtos.report;

import java.time.Instant;

public record FeeResponseDTO(
        String feeId,
        Instant startDate,
        Instant endDate,
        float pricePerHour,
        float extraHourFee
) {
}
