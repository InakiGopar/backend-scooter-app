package ar.edu.unicen.scooterservice.infrastructure.controllers;

import ar.edu.unicen.scooterservice.application.services.ScooterService;
import ar.edu.unicen.scooterservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.scooterservice.domain.dtos.request.ScooterRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterResponseDTO;
import ar.edu.unicen.scooterservice.domain.dtos.report.ScooterTripKMReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scooter")
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @PostMapping
    public ResponseEntity<ScooterResponseDTO> createScooter(@RequestBody ScooterRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scooterService.createScooter(request));
    }

    @GetMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> findScooterById(@PathVariable Long scooterId){
        return ResponseEntity.ok(scooterService.findScooterById(scooterId));
    }

    @PutMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> updateScooter(
            @PathVariable Long scooterId,
            @RequestBody ScooterRequestDTO request) {
        return ResponseEntity.ok(scooterService.updateScooter(scooterId, request));
    }

    @PatchMapping("/{scooterId}/status")
    public ResponseEntity<ScooterResponseDTO> updateScooterStatusAndStop(
            @PathVariable Long scooterId,
            @RequestBody ScooterRequestDTO request) {
        return ResponseEntity.ok(scooterService.updateScooterStatusAndStop(scooterId, request));
    }

    @DeleteMapping("/{scooterId}")
    public ResponseEntity<ScooterResponseDTO> deleteScooter(@PathVariable Long scooterId) {
        scooterService.deleteScooter(scooterId);
        return ResponseEntity.noContent().build();
    }

    //Report A
    @GetMapping("by-kilometers")
    public ResponseEntity<List<ScooterTripKMReportDTO>> getScootersReportByKilometers(@RequestParam(required = false) Boolean withPause) {
        return ResponseEntity.ok(scooterService.getScootersReportByKilometers(withPause));
    }

    //Report C
    @GetMapping("/by-travels")
    public ResponseEntity<List<ScooterTripKMReportDTO>> getScootersReportByTravels(@RequestParam int year, @RequestParam int countTrips) {
        return ResponseEntity.ok(scooterService.getScootersReportByTravels(year, countTrips));
    }

    // Report G
    @GetMapping("/near-scooters")
    public ResponseEntity<List<NearScooterReportDTO>> getNearScooters(
            @RequestParam float latitude,
            @RequestParam float longitude) {
        return ResponseEntity.ok(scooterService.getNearScooters(latitude, longitude));
    }
}
