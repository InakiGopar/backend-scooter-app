package ar.edu.unicen.userservice.domain.model.trip.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceReport {
    private Date startDate;
    private Date endDate;
    private float totalInvoiced;
    private int totalTrips;
}
