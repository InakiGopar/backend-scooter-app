package ar.edu.unicen.walletservice.domain.dtos.request;

import ar.edu.unicen.walletservice.domain.entities.Wallet;

public record WalletCreateRequestDTO(
        Long accountId,
        Long userId,
        float amount
) {
    public Wallet toEntity() {
        return new Wallet(accountId, userId, amount);
    }
}
