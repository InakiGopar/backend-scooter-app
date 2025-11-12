package ar.edu.unicen.accountservice.domain.dtos.response.account;

public record CancelAccountDTO(
        Long accountId,
        String accountName
) {
}
