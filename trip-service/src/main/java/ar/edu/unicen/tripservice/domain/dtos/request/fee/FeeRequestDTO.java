package ar.edu.unicen.tripservice.domain.dtos.request.fee;

import ar.edu.unicen.tripservice.domain.documents.Fee;

import java.time.Instant;

public record FeeRequestDTO(
        Instant startDate,
        Instant endDate,
        float pricePerHour,
        float extraHourFee
) {

    public Fee toEntity() {
        return new Fee(startDate, endDate, pricePerHour, extraHourFee);
    }
}
