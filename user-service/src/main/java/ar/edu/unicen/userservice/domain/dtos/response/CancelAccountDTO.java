package ar.edu.unicen.userservice.domain.dtos.response;

import ar.edu.unicen.userservice.domain.model.account.AccountState;

public record CancelAccountDTO(
        Long accountId,
        String accountName,
        AccountState state
) {
}
