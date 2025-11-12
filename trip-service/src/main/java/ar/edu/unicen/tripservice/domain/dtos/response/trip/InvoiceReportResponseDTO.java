package ar.edu.unicen.tripservice.domain.dtos.response.trip;

import java.util.Date;

public record InvoiceReportResponseDTO(
        float totalInvoiced,
        int totalTrips
) {

}
