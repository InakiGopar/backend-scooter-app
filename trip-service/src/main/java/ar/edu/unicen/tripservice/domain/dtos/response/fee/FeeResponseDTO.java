package ar.edu.unicen.tripservice.domain.dtos.response.fee;

import java.util.Date;

public record FeeResponseDTO(
        Long feeId,
        Date startDate,
        Date endDate,
        float pricePerHour
) {
}
