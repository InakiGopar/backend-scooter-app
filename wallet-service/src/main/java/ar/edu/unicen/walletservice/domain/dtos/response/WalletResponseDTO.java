package ar.edu.unicen.walletservice.domain.dtos.response;

public record WalletResponseDTO (
    Long walletId,
    Long accountId,
    Long userId,
    float amount
) {}
