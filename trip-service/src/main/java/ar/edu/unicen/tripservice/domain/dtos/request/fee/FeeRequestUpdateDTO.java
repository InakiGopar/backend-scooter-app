package ar.edu.unicen.tripservice.domain.dtos.request.fee;

import java.util.Date;

public record FeeRequestUpdateDTO(
        Long feeId,
        Date startDate,
        Date endDate,
        float pricePerHour
) {
}
