package ar.edu.unicen.supportservice.domain.dtos.request;

import java.util.Date;

/**
 *
 * Do we need to use all this data to create the DTO?
 * is it a good idea to use only IDs
 * like
 *
         * public record SupportRequestUpdateDTO(
         *         Long supportId,
         *         Long scooterId,
         * ) {
         * }
 * */
public record SupportRequestUpdateDTO(
        Long supportId,
        Long scooterId,
        Date startDate,
        Date endDate
) {
}
