package ar.edu.unicen.walletservice.application.services;

import ar.edu.unicen.walletservice.application.repositories.WalletRepository;
import ar.edu.unicen.walletservice.domain.dtos.request.WalletRequestDTO;
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
    public WalletResponseDTO saveWallet(WalletRequestDTO request) {

        User user = userFeignClient.findUserById(request.userId());
        Account account = accountFeignClient.findAccountById(request.accountId());

        //checks
        if (account == null && user == null){
            throw new RuntimeException("User or Account not found");
        }

        Wallet wallet = request.toEntity();
        walletRepository.save(wallet);

        return WalletResponseDTO.toDTO(wallet);
    }

    @Transactional
    public WalletResponseDTO updateWallet( Long walletId, WalletRequestDTO request ) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + walletId));

        wallet.setAmount(request.amount());
        wallet.setUserId(request.userId());
        wallet.setAccountId(request.accountId());

        walletRepository.save(wallet);

        return WalletResponseDTO.toDTO(wallet);
    }


    @Transactional
    public WalletResponseDTO updateAmount(Long userId, Long accountId  ,WalletRequestDTO request) {

        //check 1
        Wallet wallet = Objects.requireNonNull(walletRepository.findByUserIdAndAccountId(userId, accountId),
                "Wallet not found with user id: " + userId + " and account id: " + accountId);

        //updated the amount
        wallet.setAmount(request.amount());
        walletRepository.save(wallet);

        return WalletResponseDTO.toDTO(wallet);
    }


    public void deleteWallet(Long id){
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + id));
        walletRepository.delete(wallet);
    }
}
