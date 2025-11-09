package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.FeeService;
import ar.edu.unicen.tripservice.application.services.TripService;
import ar.edu.unicen.tripservice.domain.dtos.request.fee.FeeRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.fee.FeeResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fee")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;

    @PostMapping
    public ResponseEntity<FeeResponseDTO> create(@RequestBody FeeRequestDTO request) {
        return ResponseEntity.status(201).body(feeService.createFee(request));
    }

    @PutMapping("/{feeId}")
    public ResponseEntity<FeeResponseDTO> update(@PathVariable String feeId, @RequestBody FeeRequestDTO request) {
        return ResponseEntity.ok(feeService.updateFee(feeId, request));
    }

    @PatchMapping("/{feeId}/extraHour")
    public ResponseEntity<FeeResponseDTO> updateExtraHour(@PathVariable String feeId, @RequestBody FeeRequestDTO request) {
        return ResponseEntity.ok(feeService.updateFeeExtraHour(feeId, request));
    }

    @GetMapping
    public ResponseEntity<FeeResponseDTO> getById(@RequestParam String feeId) {
        return ResponseEntity.ok(feeService.getFeeById(feeId));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String feeId) {
        feeService.deleteFee(feeId);
        return ResponseEntity.noContent().build();
    }
}

