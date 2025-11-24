package ar.edu.unicen.accountservice.domain.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Long userId;
    private Long totalScooterUsage;
    private Long monthStart;
    private Long monthEnd;
}
