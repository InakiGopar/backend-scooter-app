package ar.edu.unicen.userservice.domain.model.trip.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceReport {
    private float totalInvoiced;
    private int totalTrips;
}
