package ar.edu.unicen.walletservice.infrastructure.controllers;

import ar.edu.unicen.walletservice.application.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
}
