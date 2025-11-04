package ar.edu.unicen.supportservice.domain.dtos.response;

import java.util.Date;

public record SupportResponseDTO(
        Long supportId,
        Long scooterId,
        Date startDate,
        Date endDate
) {
}
