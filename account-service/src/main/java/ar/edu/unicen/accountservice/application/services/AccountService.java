package ar.edu.unicen.accountservice.application.services;

import ar.edu.unicen.accountservice.application.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
}
