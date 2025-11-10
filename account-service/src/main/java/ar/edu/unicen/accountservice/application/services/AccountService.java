package ar.edu.unicen.accountservice.application.services;

import ar.edu.unicen.accountservice.application.repositories.AccountRepository;
import ar.edu.unicen.accountservice.domain.dtos.request.account.AccountRequestDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.account.AccountResponseDTO;
import ar.edu.unicen.accountservice.domain.entities.Account;
import ar.edu.unicen.accountservice.domain.entities.AccountState;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public AccountResponseDTO save(AccountRequestDTO request){
        Account account = request.toEntity();
        accountRepository.save(account);
        return AccountResponseDTO.toDTO(account);
    }

    @Transactional
    public AccountResponseDTO update(Long accountId, AccountRequestDTO request){
        Account account = accountRepository.findById(accountId)
         .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + accountId));
        account.setAccountName(request.accountName());
        account.setAmount(request.amount());
        account.setCreatedAt(request.createdAt());
        account.setState(request.accountState());
        account.setType(request.accountType());

        accountRepository.save(account);
        return AccountResponseDTO.toDTO(account);

    }

    @Transactional
    public AccountResponseDTO toggleAccountState(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));

        account.setState(
                account.getState() == AccountState.ENABLED ? AccountState.DISABLED : AccountState.ENABLED
        );

        accountRepository.save(account);
        return AccountResponseDTO.toDTO(account);
    }

    public AccountResponseDTO getById(Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + accountId));
        return AccountResponseDTO.toDTO(account);
    }

    public void delete(Long accountId){
        Account account  = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + accountId));
        accountRepository.delete(account);
    }

}
