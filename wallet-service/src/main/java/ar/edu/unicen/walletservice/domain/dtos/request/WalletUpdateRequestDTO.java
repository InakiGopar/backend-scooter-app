package ar.edu.unicen.walletservice.domain.dtos.request;

public record WalletUpdateRequestDTO(
        Long walletId,
        Long accountId,
        Long userId,
        float amount
) {}
