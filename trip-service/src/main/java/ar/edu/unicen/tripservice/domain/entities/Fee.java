package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    private String feeId; // would be "fee_YYYYMM"

    private Date startDate;
    private Date endDate;
    private float pricePerHour;

    public Fee(Date startDate, Date endDate, float pricePerHour) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerHour = pricePerHour;
        this.feeId = generateFeeId(startDate);
    }

    private String generateFeeId(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        return "fee_" + formatter.format(date);
    }
}