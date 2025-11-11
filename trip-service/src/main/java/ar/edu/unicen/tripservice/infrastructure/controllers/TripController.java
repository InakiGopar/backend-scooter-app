package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.TripService;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.ScooterUsageResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponseDTO> startTrip(@RequestBody TripRequestDTO dto) {
        return ResponseEntity.status(201).body(tripService.startTrip(dto));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> endTrip(@PathVariable String tripId, @RequestBody TripRequestDTO dto) {
        return ResponseEntity.ok(tripService.endTrip(tripId, dto));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> findTripById(@PathVariable String tripId) {
        return ResponseEntity.ok(tripService.findTripById(tripId));
    }

    @GetMapping
    public ResponseEntity<List<TripResponseDTO>> findAllTrips() {
        return ResponseEntity.ok(tripService.findAllTrips());
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable String tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/kilometers")
    public ResponseEntity<List<ScooterUsageResponseDTO>> findAllByPause(@RequestParam String kilometers) {
        return ResponseEntity.ok(tripService.findAllByPause(kilometers));
    }

}

