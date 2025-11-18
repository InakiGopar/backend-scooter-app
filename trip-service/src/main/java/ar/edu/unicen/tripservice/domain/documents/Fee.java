package ar.edu.unicen.tripservice.domain.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        this.feeId = generateFeeCode(startDate);
        this.extraHourFee = extraHourFee;
    }

    private String generateFeeCode(Instant startDate) {
        return "fee_" + startDate
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyMM"));
    }
}