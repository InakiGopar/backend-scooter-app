package ar.edu.unicen.tripservice.domain.dtos.request.fee;

import java.util.Date;

public record FeeRequestCreateDTO(
        Date startDate,
        Date endDate,
        float pricePerHour
) {
}
