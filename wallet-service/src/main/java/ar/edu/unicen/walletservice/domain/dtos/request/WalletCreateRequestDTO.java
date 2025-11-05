package ar.edu.unicen.walletservice.domain.dtos.request;

public record WalletCreateRequestDTO(
        Long accountId,
        Long userId,
        float amount
) {}
