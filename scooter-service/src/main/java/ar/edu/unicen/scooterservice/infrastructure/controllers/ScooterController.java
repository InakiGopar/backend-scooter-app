package ar.edu.unicen.scooterservice.infrastructure.controllers;

import ar.edu.unicen.scooterservice.application.services.ScooterService;
import ar.edu.unicen.scooterservice.domain.dtos.request.ScooterRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/scooter")
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @PostMapping
    public ResponseEntity<ScooterResponseDTO> create(@RequestBody ScooterRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scooterService.createScooter(request));
    }

    @GetMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> getById(@PathVariable Long scooterId){
        return ResponseEntity.ok(scooterService.getScooterById(scooterId));
    }

    @PutMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> update(
            @PathVariable Long scooterId,
            @RequestBody ScooterRequestDTO request) {
        return ResponseEntity.ok(scooterService.updateScooter(scooterId, request));
    }

    @PatchMapping("/{scooterId}/status")
    public ResponseEntity<ScooterResponseDTO> updateStatusAndStop(
            @PathVariable Long scooterId,
            @RequestBody ScooterRequestDTO request) {
        return ResponseEntity.ok(scooterService.updateScooterStatusAndStop(scooterId, request));
    }

    @DeleteMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> delete(@PathVariable Long scooterId) {
        scooterService.deleteScooter(scooterId);
        return ResponseEntity.noContent().build();
    }
}
