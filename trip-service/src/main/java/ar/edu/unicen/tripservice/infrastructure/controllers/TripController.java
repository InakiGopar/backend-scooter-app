package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.TripService;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.Instant;

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
    @GetMapping("/by-kilometers")
    public ResponseEntity<List<ScooterUsageResponseDTO>> findAllByPause(
            @RequestParam(required = false, defaultValue = "false") boolean withPause) {
        return ResponseEntity.ok(tripService.findAllByKilometers(withPause));
    }

    @GetMapping("/scooter-by-trips")
    public ResponseEntity<List<TripScooterByYearResponseDTO>> getScooterByTravels(int year, int cantTrips){
        return ResponseEntity.ok(tripService.getScooterByTravels(year,cantTrips));
    }

    //Reporte D
    @GetMapping("/total-invoice")
    public ResponseEntity<InvoiceReportResponseDTO> getTotalInvoice(
            @RequestParam int year,
            @RequestParam int startMonth,
            @RequestParam int endMonth) {


        return ResponseEntity.ok(tripService.getTotalInvoice( year, startMonth, endMonth ));
    }
    @GetMapping("/scooter-user-usage")
    public ResponseEntity<List<TripScooterUserUsageDTO>>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd){
        return ResponseEntity.ok(tripService.getScooterUserUsage(monthStart,monthEnd));
    }

    @GetMapping("/user-usage-period")
    public ResponseEntity<List<UserPeriodUsageResponseDTO>> getUserUsagePeriod(
            @RequestParam Long userId,
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam(required = false) Long accountId
    ){
        Instant startInstant = Instant.parse(start);
        Instant endInstant = Instant.parse(end);

        return ResponseEntity.ok(tripService.getUserUsagePeriod(userId, startInstant, endInstant, accountId));
    }

}
