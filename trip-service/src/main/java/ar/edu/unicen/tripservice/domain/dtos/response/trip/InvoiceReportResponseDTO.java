package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import java.util.Date;

public record InvoiceReportResponseDTO(
        Date startDate,
        Date endDate,
        float totalInvoiced,
        int totalTrips
) {

}
