package ar.edu.unicen.userservice.infrastructure.controllers;

import ar.edu.unicen.userservice.application.services.UserService;
import ar.edu.unicen.userservice.domain.dtos.report.InvoiceReportDTO;
import ar.edu.unicen.userservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.userservice.domain.dtos.request.UserRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.response.CancelAccountDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserScooterUsageResponseDTO;
import ar.edu.unicen.userservice.domain.model.account.AccountType;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId, @RequestBody UserRequestDTO request){
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    //Report A
    @GetMapping("/scooters")
    public ResponseEntity<List<Scooter>>getScootersReportByKilometers(@RequestParam(required = false) Boolean withPause){
        return ResponseEntity.ok(userService.getScootersReportByKilometers(withPause));
    }

    //Report B
    @PatchMapping("/toggleState/{accountId}")
    public ResponseEntity<CancelAccountDTO> toggleAccountState(@PathVariable Long accountId) {
        return ResponseEntity.ok(userService.toggleAccountState(accountId));
    }


    //Report C
    @GetMapping("/scooters/usage")
    public ResponseEntity<List<Scooter>> getScootersReportByTravels(
            @RequestParam int year,
            @RequestParam int countTrips) {
        return ResponseEntity.ok(userService.getScootersReportByTravels(year, countTrips));
    }


    //Report D
    @GetMapping("/total-invoice")
    public ResponseEntity<InvoiceReportDTO> getTotalInvoice(
            @RequestParam int year,
            @RequestParam int startMonth,
            @RequestParam int endMonth) {

        return ResponseEntity.ok(  userService.getTotalInvoiceReport(year, startMonth, endMonth) );
    }


    //Report E
    @GetMapping("/scooter-user-usage")
    public ResponseEntity<List<UserScooterUsageResponseDTO>>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd, @RequestParam AccountType userType){
        return ResponseEntity.ok(userService.getScooterUserUsage(monthStart,monthEnd,userType));
    }


    //Report G
    @GetMapping("/near-scooters")
    public ResponseEntity<List<NearScooterReportDTO>> getNearScooters(
            @RequestParam float latitude, @RequestParam float longitude
    ) {
        return ResponseEntity.ok( userService.getNearScooters(latitude, longitude) );
    }

}
