package ar.edu.unicen.walletservice.infrastructure.controllers;

import ar.edu.unicen.walletservice.application.services.WalletService;
import ar.edu.unicen.walletservice.domain.dtos.request.WalletRequestDTO;
import ar.edu.unicen.walletservice.domain.dtos.response.WalletResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponseDTO> saveWallet(@RequestBody WalletRequestDTO request){

        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.saveWallet(request));
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<WalletResponseDTO> updateWallet(@PathVariable Long walletId, @RequestBody WalletRequestDTO request){
        return ResponseEntity.ok(walletService.updateWallet(walletId,request));
    }

    @PatchMapping("/userId/{userId}/accountId/{accountId}")
    public ResponseEntity<WalletResponseDTO> updateAmount(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @RequestBody WalletRequestDTO request ) {
        return ResponseEntity.ok(walletService.updateAmount(userId, accountId ,request));
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long walletId){
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }
}
