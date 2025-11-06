package ar.edu.unicen.accountservice.domain.dtos.request.account;

import ar.edu.unicen.accountservice.domain.entities.Account;
import ar.edu.unicen.accountservice.domain.entities.AccountState;
import ar.edu.unicen.accountservice.domain.entities.AccountType;

import java.sql.Date;

public record AccountRequestDTO(
        String accountName,
        float amount,
        Date createdAt,
        AccountState accountState,
        AccountType accountType
) {
    public Account toEntity(){
        return new Account(
                this.accountName,
                this.amount,
                this.createdAt,
                this.accountState,
                this.accountType
        );
    }
}
