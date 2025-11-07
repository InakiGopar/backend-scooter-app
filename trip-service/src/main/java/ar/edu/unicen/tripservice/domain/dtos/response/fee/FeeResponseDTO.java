package ar.edu.unicen.tripservice.domain.dtos.response.fee;

import ar.edu.unicen.tripservice.domain.entities.Fee;

import java.util.Date;

public record FeeResponseDTO(
        String feeId,
        Date startDate,
        Date endDate,
        float pricePerHour
) {
    public static FeeResponseDTO toDTO(Fee fee) {
        return new FeeResponseDTO(fee.getFeeId(),  fee.getStartDate(), fee.getEndDate(), fee.getPricePerHour());
    }
}
