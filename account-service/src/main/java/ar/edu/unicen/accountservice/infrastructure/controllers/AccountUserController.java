package ar.edu.unicen.accountservice.infrastructure.controllers;

import ar.edu.unicen.accountservice.application.services.AccountUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/AccountUser")
@RequiredArgsConstructor
public class AccountUserController {
    private final AccountUserService accountUserService;
}
