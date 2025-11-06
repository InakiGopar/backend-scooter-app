package ar.edu.unicen.accountservice.domain.dtos.response.account;

import ar.edu.unicen.accountservice.domain.entities.AccountUser;

public record AccountUserResponseDTO(
        Long accountId,
        Long userId
) {
    public static AccountUserResponseDTO toDTO(AccountUser accountUser) {
        return new AccountUserResponseDTO(
                accountUser.getId().getAccountId(),
                accountUser.getId().getUserId()
        );
    }
}
