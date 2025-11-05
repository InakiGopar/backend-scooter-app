package ar.edu.unicen.walletservice.domain.dtos.response;

import ar.edu.unicen.walletservice.domain.entities.Wallet;

public record WalletResponseDTO (
    Long walletId,
    Long accountId,
    Long userId,
    float amount
) {
    public static WalletResponseDTO toDTO(Wallet wallet) {
        return new WalletResponseDTO(
                wallet.getWalletId(),
                wallet.getAccountId(),
                wallet.getUserId(),
                wallet.getAmount()
        );
    }
}
