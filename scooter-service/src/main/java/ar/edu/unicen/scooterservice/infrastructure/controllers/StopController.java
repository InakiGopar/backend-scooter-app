package ar.edu.unicen.scooterservice.infrastructure.controllers;

import ar.edu.unicen.scooterservice.application.services.StopService;
import ar.edu.unicen.scooterservice.domain.dtos.request.StopRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.StopResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/stop")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;

    @PostMapping
    public ResponseEntity<StopResponseDTO> create(@RequestBody StopRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stopService.createStop(request));
    }

    @PutMapping("/{stopId}")
    public ResponseEntity<StopResponseDTO> update( @PathVariable Long stopId, @RequestBody StopRequestDTO request) {
        return ResponseEntity.ok(stopService.updateStop( stopId, request ));
    }

    @DeleteMapping("/{stopId}")
    public ResponseEntity<Void> delete(@PathVariable Long stopId) {
        stopService.deleteStop(stopId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<StopResponseDTO> get(@PathVariable Long stopId) {
        return ResponseEntity.ok(stopService.getStop(stopId));
    }
}
