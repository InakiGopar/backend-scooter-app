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

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserFeignClient userFeignClient;
    private final AccountFeignClient accountFeignClient;

    @Transactional
    public WalletResponseDTO saveWallet(WalletCreateRequestDTO request) {
        User user = userFeignClient.findUserById(request.userId());
        Account account = accountFeignClient.findAccountById(request.accountId());
        if(account == null && user == null){
            throw new RuntimeException("User or Account not found");
        }
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

  //todo cambiar el nombre del método

    @Transactional
    public WalletResponseDTO patchAmount(Long userId, Long accountId  ,WalletUpdateRequestDTO request) {

        Wallet wallet = Objects.requireNonNull(walletRepository.findByUserIdAndAccountId(userId, accountId),
                "Wallet not found with user id: " + userId + " and account id: " + accountId);

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
