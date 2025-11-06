package ar.edu.unicen.supportservice.domain.dtos.response;

import ar.edu.unicen.supportservice.domain.entities.Support;

import java.util.Date;

public record SupportResponseDTO(
        Long supportId,
        Long scooterId,
        Date startDate,
        Date endDate
) {
    public static SupportResponseDTO toDTO(Support support) {
        return new SupportResponseDTO(
                support.getSupportId(),
                support.getScooterId(),
                support.getStartDate(),
                support.getEndDate()
        );
    }
}
