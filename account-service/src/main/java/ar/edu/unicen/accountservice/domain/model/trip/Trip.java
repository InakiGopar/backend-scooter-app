package ar.edu.unicen.accountservice.domain.model.trip;

import ar.edu.unicen.accountservice.domain.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Long userID;
    private int countScooterUsage;
    private int monthStart;
    private int monthEnd;
    private AccountType userType;
}
