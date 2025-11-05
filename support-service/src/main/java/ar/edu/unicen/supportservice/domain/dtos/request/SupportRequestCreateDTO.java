package ar.edu.unicen.supportservice.domain.dtos.request;

import ar.edu.unicen.supportservice.domain.entities.Support;

import java.util.Date;

public record SupportRequestCreateDTO(
        Long scooterId,
        Date startDate,
        Date endDate

) {

    public Support toEntity() {
        return new Support(
            scooterId,
            startDate,
            endDate
        );
    }
}
