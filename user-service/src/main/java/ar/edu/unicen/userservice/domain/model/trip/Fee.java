package ar.edu.unicen.userservice.domain.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    private Instant startDate;
    private Instant endDate;
    private float pricePerHour;
    private float extraHourFee;
}
