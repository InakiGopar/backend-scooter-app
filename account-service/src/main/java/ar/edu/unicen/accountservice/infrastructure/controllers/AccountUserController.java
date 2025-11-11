package ar.edu.unicen.accountservice.infrastructure.controllers;

import ar.edu.unicen.accountservice.application.services.AccountService;
import ar.edu.unicen.accountservice.application.services.AccountUserService;
import ar.edu.unicen.accountservice.domain.dtos.request.accountUser.AccountUserRequestDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.accountUser.AccountUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/AccountUser")
@RequiredArgsConstructor
public class AccountUserController {
    private final AccountUserService accountUserService;
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public  ResponseEntity<List<AccountUserResponseDTO>> getAccountUser(@PathVariable Long accountId )
    {
        return ResponseEntity.ok(accountUserService.getAccountUserByAccount(accountId));
    }
    @PostMapping
    public ResponseEntity<AccountUserResponseDTO> createAccountUser(@RequestBody AccountUserRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountUserService.createAccountUser(request));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAccountUser(@RequestBody AccountUserRequestDTO request) {
        accountUserService.deleteAccountUser(request);
        return ResponseEntity.noContent().build();
    }
}
