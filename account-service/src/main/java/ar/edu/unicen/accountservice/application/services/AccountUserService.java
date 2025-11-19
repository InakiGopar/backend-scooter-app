package ar.edu.unicen.accountservice.application.services;

import ar.edu.unicen.accountservice.application.repositories.AccountUserRepository;
import ar.edu.unicen.accountservice.domain.dtos.request.accountUser.AccountUserRequestDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.accountUser.AccountUserResponseDTO;
import ar.edu.unicen.accountservice.domain.entities.AccountUser;
import ar.edu.unicen.accountservice.domain.entities.AccountUserID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountUserService {
    private final AccountUserRepository accountUserRepository;

    @Transactional
    public AccountUserResponseDTO createAccountUser(AccountUserRequestDTO request) {
        AccountUserID accountUserID = new AccountUserID(request.accountId(), request.userId());

        if(accountUserRepository.existsById(accountUserID)) {
            throw new RuntimeException("AccountUser already exists with accountId: " + request.accountId() + " and userId: " + request.userId());
        }
        AccountUser accountUser = new AccountUser(accountUserID);
        accountUserRepository.save(accountUser);
        return AccountUserResponseDTO.toDTO(accountUser);
    }
   public void deleteAccountUser(AccountUserRequestDTO request) {
        AccountUserID accountUserID = new AccountUserID(request.accountId(), request.userId());

        if(!accountUserRepository.existsById(accountUserID)) {
            throw new RuntimeException("AccountUser not found with accountId: " + request.accountId() + " and userId: " + request.userId());
        }
        accountUserRepository.deleteById(accountUserID);
    }
    public List<AccountUserResponseDTO> getAccountUserByAccount(Long accountId) {
        return accountUserRepository.findAllByAccountId(accountId)
                .stream()
                .map(a -> new AccountUserResponseDTO(a.getId().getAccountId(), a.getId().getUserId()))
                .toList();
    }
}
