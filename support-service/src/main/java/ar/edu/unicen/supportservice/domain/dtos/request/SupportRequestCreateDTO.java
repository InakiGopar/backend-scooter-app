package ar.edu.unicen.supportservice.domain.dtos.request;

import java.util.Date;

public record SupportRequestCreateDTO(
        Long scooterId,
        Date startDate,
        Date endDate

) {
}
