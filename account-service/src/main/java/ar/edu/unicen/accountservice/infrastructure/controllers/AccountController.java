package ar.edu.unicen.accountservice.infrastructure.controllers;

import ar.edu.unicen.accountservice.application.services.AccountService;
import ar.edu.unicen.accountservice.domain.dtos.request.account.AccountRequestDTO;
import ar.edu.unicen.accountservice.domain.dtos.response.account.AccountResponseDTO;
import ar.edu.unicen.accountservice.domain.entities.Account;
import ar.edu.unicen.accountservice.domain.entities.AccountType;
import ar.edu.unicen.accountservice.domain.model.trip.Trip;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> saveAccount(@RequestBody AccountRequestDTO account){
        AccountResponseDTO response = accountService.saveAccount(account);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{accountId}]")
    public ResponseEntity<AccountResponseDTO>  updateAccount(@PathVariable Long accountId,@RequestBody AccountRequestDTO request){
        AccountResponseDTO response = accountService.updateAccount(accountId,request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/toggleState/{accountId}")
    public ResponseEntity<AccountResponseDTO> toggleAccountState(@PathVariable Long accountId){
        AccountResponseDTO response = accountService.toggleAccountState(accountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO>findAccountById(@PathVariable Long accountId){
        AccountResponseDTO response = accountService.findAccountById(accountId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/scooter-user-usage")
    public ResponseEntity<List<Trip>>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd, @RequestParam AccountType userType){
        return ResponseEntity.ok(accountService.getScooterUserUsage(monthStart,monthEnd,userType));
    }
}
