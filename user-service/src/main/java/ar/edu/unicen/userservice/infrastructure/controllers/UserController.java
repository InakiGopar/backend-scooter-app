package ar.edu.unicen.userservice.infrastructure.controllers;

import ar.edu.unicen.userservice.application.services.UserService;
import ar.edu.unicen.userservice.domain.dtos.request.UserRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import ar.edu.unicen.userservice.domain.model.trip.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId, @RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userId, request));
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
    @GetMapping("/scooters")
    public ResponseEntity<List<Scooter>>getScootersReportByKilometers(@RequestParam(required = false) Boolean withPause){
        return ResponseEntity.ok(userService.getScootersReportByKilometers(withPause));
    }

    @GetMapping("scooters/usage")
    public ResponseEntity<List<Scooter>> getScootersReportByTravels(
            @RequestParam int year,
            @RequestParam int countTrips) {
        return ResponseEntity.ok(userService.getScootersReportByTravels(year, countTrips));
    }

}
