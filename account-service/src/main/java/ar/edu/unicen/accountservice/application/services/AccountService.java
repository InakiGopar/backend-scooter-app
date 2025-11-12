package ar.edu.unicen.accountservice.application.services;

import ar.edu.unicen.accountservice.application.repositories.AccountRepository;
import ar.edu.unicen.accountservice.domain.dtos.request.account.AccountRequestDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.account.AccountResponseDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.account.CancelAccountDTO;
import ar.edu.unicen.accountservice.domain.entities.Account;
import ar.edu.unicen.accountservice.domain.entities.AccountState;
import ar.edu.unicen.accountservice.domain.entities.AccountType;
import ar.edu.unicen.accountservice.domain.model.trip.Trip;
import ar.edu.unicen.accountservice.infrastructure.feignClients.TripFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TripFeignClient tripFeignClient;


    @Transactional
    public AccountResponseDTO saveAccount(AccountRequestDTO request) {
        Account account = request.toEntity();
        accountRepository.save(account);
        return AccountResponseDTO.toDTO(account);
    }

    @Transactional
    public AccountResponseDTO updateAccount(Long accountId, AccountRequestDTO request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + accountId));
        account.setAccountName(request.accountName());
        account.setAmount(request.amount());
        account.setCreatedAt(request.createdAt());
        account.setState(request.accountState());
        account.setType(request.accountType());

        accountRepository.save(account);
        return AccountResponseDTO.toDTO(account);

    }

    public AccountResponseDTO findAccountById(Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + accountId));
        return AccountResponseDTO.toDTO(account);
    }


    public void deleteAccount(Long accountId){
        Account account  = accountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: " + accountId));
        accountRepository.delete(account);
    }

    //Report B
    @Transactional
    public CancelAccountDTO toggleAccountState(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));

        account.setState(
                account.getState() == AccountState.ENABLED ? AccountState.DISABLED : AccountState.ENABLED
        );

        accountRepository.save(account);

        return new CancelAccountDTO(account.getAccountId(), account.getAccountName());
    }

    //Report E
    public List<Trip> getScooterUserUsage(int monthStart, int monthEnd, AccountType userType){

        return tripFeignClient.getScooterUserUsage(monthStart,monthEnd,userType).stream()
                .filter(trip -> trip.getUserType().equals(userType))
                .map(dto -> new Trip(dto.getUserID(),dto.getCountScooterUsage(), dto.getMonthStart(), dto.getMonthEnd(),dto.getUserType()))
                .toList();
    }


}
