package ar.edu.unicen.walletservice.application.services;

import ar.edu.unicen.walletservice.application.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
}
