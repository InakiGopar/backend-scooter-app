package ar.edu.unicen.accountservice.domain.dtos.report;

import ar.edu.unicen.accountservice.domain.entities.AccountState;
import ar.edu.unicen.accountservice.domain.entities.AccountType;
import ar.edu.unicen.accountservice.domain.model.trip.Trip;

public record UserUsageScooterDTO(
        Long userId,
        Long totalScooterUsage,
        Long monthStart,
        Long monthEnd,
        AccountType accountType
) {
    public static UserUsageScooterDTO toDTO(Trip trip, AccountType type) {
        return new UserUsageScooterDTO(
                trip.getUserId(),
                trip.getTotalScooterUsage(),
                trip.getMonthStart(),
                trip.getMonthEnd(),
                type
        );
    }
}
