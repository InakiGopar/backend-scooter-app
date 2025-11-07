package ar.edu.unicen.tripservice.domain.dtos.request.fee;

import ar.edu.unicen.tripservice.domain.entities.Fee;

import java.util.Date;

public record FeeRequestDTO(
        Date startDate,
        Date endDate,
        float pricePerHour
) {

    public Fee toEntity() {
        return new Fee(startDate, endDate, pricePerHour);
    }
}
