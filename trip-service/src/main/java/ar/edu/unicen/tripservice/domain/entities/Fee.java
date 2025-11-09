package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.text.SimpleDateFormat;
import java.time.Instant;

@Document(collection = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    private String feeId; // would be "fee_YYYYMM"

    private Instant startDate;
    private Instant endDate;
    private float pricePerHour;
    private float extraHourFee;

    public Fee(Instant startDate, Instant endDate, float pricePerHour, float extraHourFee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerHour = pricePerHour;
        this.feeId = generateFeeId(startDate);
        this.extraHourFee = extraHourFee;
    }

    private String generateFeeId(Instant startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        return "fee_" + formatter.format(startDate);
    }
}