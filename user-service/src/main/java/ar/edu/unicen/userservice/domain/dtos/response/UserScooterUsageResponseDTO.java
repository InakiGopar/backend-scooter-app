package ar.edu.unicen.userservice.domain.dtos.response;

import ar.edu.unicen.userservice.domain.model.account.AccountType;

public record UserScooterUsageResponseDTO(
        Long userId,
        int countScooterUsage,
        int monthStart,
        int monthEnd,
        AccountType userType
) {
}
