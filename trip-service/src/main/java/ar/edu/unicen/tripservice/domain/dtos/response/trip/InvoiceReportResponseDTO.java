package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record InvoiceReportResponseDTO(
        float totalInvoiced,
        int totalTrips
) {

}
