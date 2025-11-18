package ar.edu.unicen.tripservice.domain.dtos.response.fee;

import ar.edu.unicen.tripservice.domain.documents.Fee;
import java.time.Instant;

public record FeeResponseDTO(
        String feeId,
        Instant startDate,
        Instant endDate,
        float pricePerHour,
        float extraHourFee
) {
    public static FeeResponseDTO toDTO(Fee fee) {
        return new FeeResponseDTO(
                fee.getFeeId(),
                fee.getStartDate(),
                fee.getEndDate(),
                fee.getPricePerHour(),
                fee.getExtraHourFee());
    }
}
