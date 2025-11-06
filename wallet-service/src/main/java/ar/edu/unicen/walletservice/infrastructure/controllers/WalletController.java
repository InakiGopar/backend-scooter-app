package ar.edu.unicen.walletservice.infrastructure.controllers;

import ar.edu.unicen.walletservice.application.services.WalletService;
import ar.edu.unicen.walletservice.domain.dtos.request.WalletRequestDTO;
import ar.edu.unicen.walletservice.domain.dtos.response.WalletResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponseDTO> saveWallet(@RequestBody WalletRequestDTO request){
        WalletResponseDTO response = walletService.saveWallet(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<WalletResponseDTO> updateWallet(@PathVariable Long id, @RequestBody WalletRequestDTO request){
        WalletResponseDTO response = walletService.updateWallet(id,request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/userId/{userId}/accountId/{accountId}")
    public ResponseEntity<WalletResponseDTO> patchAmount(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @RequestBody WalletRequestDTO request ) {
        WalletResponseDTO response = walletService.updateAmount(userId, accountId ,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id){
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}
