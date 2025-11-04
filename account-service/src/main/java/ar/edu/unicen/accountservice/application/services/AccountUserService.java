package ar.edu.unicen.accountservice.application.services;

import ar.edu.unicen.accountservice.application.repositories.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUserService {
    private final AccountUserRepository accountUserRepository;
}
