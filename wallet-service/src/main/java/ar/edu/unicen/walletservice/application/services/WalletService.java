package ar.edu.unicen.walletservice.application.services;

import ar.edu.unicen.walletservice.application.repositories.WalletRepository;
import ar.edu.unicen.walletservice.domain.dtos.request.WalletCreateRequestDTO;
import ar.edu.unicen.walletservice.domain.dtos.request.WalletUpdateRequestDTO;
import ar.edu.unicen.walletservice.domain.dtos.response.WalletResponseDTO;
import ar.edu.unicen.walletservice.domain.entities.Wallet;
import ar.edu.unicen.walletservice.domain.model.Account;
import ar.edu.unicen.walletservice.domain.model.User;
import ar.edu.unicen.walletservice.infrastructure.feingClients.AccountFeignClient;
import ar.edu.unicen.walletservice.infrastructure.feingClients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserFeignClient userFeignClient;
    private final AccountFeignClient accountFeignClient;
    @Transactional
    public WalletResponseDTO saveWallet(WalletCreateRequestDTO request){
        User user = userFeignClient.findUserById(request.userId());
        Account account = accountFeignClient.findAccountById(request.accountId());
        Wallet wallet = new Wallet();
        wallet.setUserId(request.userId());
        wallet.setAccountId(request.accountId());
        wallet.setAmount(request.amount());
        return new WalletResponseDTO(
                wallet.getWalletId(),
                wallet.getUserId(),
                wallet.getAccountId(),
                wallet.getAmount()
        );
    }
    @Transactional
    public WalletResponseDTO updateWallet(Long walletId,WalletUpdateRequestDTO request){
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + walletId));;

        wallet.setAmount(request.amount());
        walletRepository.save(wallet);
        return new WalletResponseDTO(
                wallet.getWalletId(),
                wallet.getUserId(),
                wallet.getAccountId(),
                wallet.getAmount()
        );

    }
    @Transactional
    public WalletResponseDTO patchAmount(Long walletId, WalletUpdateRequestDTO request){
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + walletId));
        if(!(wallet.getAmount()>0 && wallet.getAmount()>request.amount())){
            throw new RuntimeException("Insufficient funds in wallet with id: " + walletId);
        }

        float resta = wallet.getAmount()-request.amount();
        wallet.setAmount(resta);

        walletRepository.save(wallet);

        return new WalletResponseDTO(
                wallet.getWalletId(),
                wallet.getUserId(),
                wallet.getAccountId(),
                wallet.getAmount()
        );
    }
    public void deleteWallet(Long id){
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + id));
        walletRepository.delete(wallet);
    }
}
