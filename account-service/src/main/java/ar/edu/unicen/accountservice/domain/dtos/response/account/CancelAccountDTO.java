package ar.edu.unicen.accountservice.domain.dtos.response.account;

import ar.edu.unicen.accountservice.domain.entities.AccountState;

public record CancelAccountDTO(
        Long accountId,
        String accountName,
        AccountState state
) {
}
