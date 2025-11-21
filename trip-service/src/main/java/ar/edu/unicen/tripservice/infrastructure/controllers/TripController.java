package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.TripService;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponseDTO> startTrip(@RequestBody TripRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.startTrip(request));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> endTrip(@PathVariable String tripId, @RequestBody TripRequestDTO request) {
        return ResponseEntity.ok(tripService.endTrip(tripId, request));
    }

    @PatchMapping("/startPauseTrip/{tripId}")
    public ResponseEntity<TripResponseDTO> startPauseTrip(@PathVariable String tripId) {
        return ResponseEntity.ok(tripService.startPauseTrip(tripId));
    }

    @PatchMapping("/endPauseTrip/{tripId}")
    public ResponseEntity<TripResponseDTO> endPauseTrip(@PathVariable String tripId) {
        return ResponseEntity.ok(tripService.endPauseTrip(tripId));
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

    //Report A
    @GetMapping("/by-kilometers")
    public ResponseEntity<List<ScooterUsageResponseDTO>> findAllByPause(
            @RequestParam(required = false, defaultValue = "false") Boolean withPause) {
        return ResponseEntity.ok(tripService.findAllByKilometers(withPause));
    }

    //Report C
    @GetMapping("/scooter-by-trips")
    public ResponseEntity<List<TripScooterByYearResponseDTO>> getScooterByTravels( @RequestParam int year,
                                                                                   @RequestParam int countTrips)
    {
        return ResponseEntity.ok(tripService.getScooterByTravels(year, countTrips));
    }

    //Report D
    @GetMapping("/total-invoice")
    public ResponseEntity<InvoiceReportResponseDTO> getTotalInvoice(
            @RequestParam int year,
            @RequestParam int startMonth,
            @RequestParam int endMonth) {
        return ResponseEntity.ok(tripService.getTotalInvoice( year, startMonth, endMonth ));
    }

    //Report E
    @GetMapping("/scooter-user-usage")
    public ResponseEntity<List<TripScooterUserUsageDTO>>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd){
        return ResponseEntity.ok(tripService.getScooterUserUsage(monthStart,monthEnd));
    }

    //Report H (two methods)
    @GetMapping("/scooter-usages-by-period/{userId}")
    public ResponseEntity<List<UserScooterPeriodUsageDTO>> getUserUsagePeriod(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd
    ){
        return ResponseEntity.ok(tripService.getUserUsagePeriod(userId, year ,monthStart, monthEnd));
    }

    @GetMapping("/scooter-usages-by-period")
    public ResponseEntity<List<UserScooterPeriodUsageDTO>> getUsagePeriodForUsersByAccount(
            @RequestParam List<Long> userIds,
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd
    ) {
        return ResponseEntity.ok(
                tripService.getUsagePeriodForUsersByAccount(userIds, year, monthStart, monthEnd)
        );
    }

    @GetMapping("/stats/{userId}")
    public ResponseEntity<TripStatsDTO> getTripStats(@PathVariable Long userId) {
        return ResponseEntity.ok(tripService.getTripsStats(userId));
    }
}
