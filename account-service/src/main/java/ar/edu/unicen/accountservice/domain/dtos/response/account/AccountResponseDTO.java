package ar.edu.unicen.accountservice.domain.dtos.response.account;

import ar.edu.unicen.accountservice.domain.entities.Account;
import ar.edu.unicen.accountservice.domain.entities.AccountState;
import ar.edu.unicen.accountservice.domain.entities.AccountType;

import java.util.Date;

public record AccountResponseDTO(
    Long accountId,
    String accountName,
    Date createdAt,
    float amount,
    AccountState accountState,
    AccountType accountType
) {
    public static AccountResponseDTO toDTO(Account account) {
        return new AccountResponseDTO(
            account.getAccountId(),
            account.getAccountName(),
            account.getCreatedAt(),
            account.getAmount(),
            account.getState(),
            account.getType()
        );
    }
}
