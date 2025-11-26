package ar.edu.unicen.userservice.infrastructure.controllers;

import ar.edu.unicen.userservice.application.services.UserService;
import ar.edu.unicen.userservice.domain.dtos.report.*;
import ar.edu.unicen.userservice.domain.dtos.request.PromptRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.request.UserRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.response.*;
import ar.edu.unicen.userservice.domain.model.account.AccountType;
import ar.edu.unicen.userservice.domain.model.trip.Fee;
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

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<AuthUserResponseDTO> findByEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.findByEmail(userEmail));
    }

    //Report A
    @GetMapping("/scooters")
    public ResponseEntity<List<ReportADTO>>getScootersReportByKilometers(@RequestParam(required = false) Boolean withPause){
        return ResponseEntity.ok(userService.getScootersReportByKilometers(withPause));
    }

    //Report B
    @PatchMapping("/toggleState/{accountId}")
    public ResponseEntity<CancelAccountDTO> toggleAccountState(@PathVariable Long accountId) {
        return ResponseEntity.ok(userService.toggleAccountState(accountId));
    }


    //Report C
    @GetMapping("/scooters/usage")
    public ResponseEntity<List<ReportScooterByYearDTO>> getScootersReportByTravels(
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

        return ResponseEntity.ok(userService.getTotalInvoiceReport(year, startMonth, endMonth));
    }


    //Report E
    @GetMapping("/scooter-user-usage")
    public ResponseEntity<List<UserScooterUsageResponseDTO>>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd, @RequestParam AccountType userType){
        return ResponseEntity.ok(userService.getScooterUserUsage(monthStart,monthEnd,userType));
    }


    //Report F
    @PostMapping("/fee")
    public ResponseEntity<FeeResponseDTO> createFee(@RequestBody Fee requestFee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createFee(requestFee));
    }

    //Report G
    @GetMapping("/near-scooters")
    public ResponseEntity<List<NearScooterReportDTO>> getNearScooters(
            @RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius
    )
    {
        return ResponseEntity.ok( userService.getNearScooters(latitude, longitude, radius) );
    }

    //Report H
    @GetMapping("/scooter-usages-by-period/{userId}")
    public ResponseEntity<List<UserScooterPeriodUsageDTO>> getScooterUsesByPeriod(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd,
            @RequestParam(required = false) Boolean withRelatedToMyAccount
    )
    {
        return ResponseEntity.ok( userService.getScooterUsesByPeriod( userId, year ,monthStart, monthEnd, withRelatedToMyAccount ) );
    }


    //Chatbot
    @PostMapping("/chat")
    public ResponseEntity<LLMResponseDTO> chat(@RequestBody PromptRequestDTO request) {
        return ResponseEntity.ok(userService.chat(request));
    }

    @PostMapping("/historical-data/{userId}")
    public ResponseEntity<LLMResponseDTO> historicalTripData(
            @PathVariable Long userId,
            @RequestBody PromptRequestDTO request)
    {
        return ResponseEntity.ok(userService.historicalTripData(userId, request));
    }

}
