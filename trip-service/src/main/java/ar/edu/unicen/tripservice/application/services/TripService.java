package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.helper.TripCostCalculator;
import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.scooter.FeignScooterEndTripRequest;
import ar.edu.unicen.tripservice.domain.dtos.request.scooter.FeignScooterPatchRequest;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.fee.FeeResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.*;
import ar.edu.unicen.tripservice.domain.documents.Trip;
import ar.edu.unicen.tripservice.domain.model.scooter.Scooter;
import ar.edu.unicen.tripservice.domain.model.scooter.ScooterState;
import ar.edu.unicen.tripservice.domain.model.scooter.Stop;
import ar.edu.unicen.tripservice.domain.model.user.User;
import ar.edu.unicen.tripservice.infrastructure.feingClients.ScooterFeignClient;
import ar.edu.unicen.tripservice.infrastructure.feingClients.UserFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final FeeService feeService;
    private final UserFeignClient userFeignClient;
    private final ScooterFeignClient scooterFeignClient;

    //Report A
    public List<ScooterUsageResponseDTO> findAllByKilometers(Boolean withPause) {
        if(!withPause) {
            return tripRepository.findAllByKilometers();
        }
        System.out.println(tripRepository.findAllByKilometersAndPause());
        return tripRepository.findAllByKilometersAndPause();
    }

    //Report C
    public List<TripScooterByYearResponseDTO> getScooterByTravels(int year, int cantTrips){
        return tripRepository.getScooterByTripInAYear(year, cantTrips);
    }

    public TripResponseDTO startTrip(TripRequestDTO request) {
        User user = userFeignClient.getUserById(request.userId());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + request.userId() + " not found");
        }

        Scooter scooter = scooterFeignClient.getScooterById(request.scooterId());

        if (scooter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scooter with id " + request.scooterId() + " not found");
        }

        ScooterState scooterState = scooter.getState();

        if (scooterState.equals(ScooterState.ACTIVE) || scooterState.equals(ScooterState.MAINTENANCE)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot start trip. Scooter is not available.");
        }

        scooterFeignClient.updateScooterStatus(
                scooter.getScooterId(),
                new FeignScooterPatchRequest(scooter.getScooterId(), ScooterState.ACTIVE)
        );

        Trip trip = request.toEntity();
        trip.setStartTime(Instant.now());
        tripRepository.save(trip);
        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO endTrip(String tripId, TripRequestDTO request){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        Scooter scooter = scooterFeignClient.getScooterById(request.scooterId());

        if (scooter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scooter with id " + request.scooterId() + " not found");
        }

        Stop endStop = scooterFeignClient.getStopById(request.stopEndId());

        if (endStop == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stop with id " + request.stopEndId() + " not found");
        }

        if (trip.getStartTime() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip has not been started.");
        }

        scooterFeignClient.updateScooterWhenTripEnd(
                request.scooterId(),
                new FeignScooterEndTripRequest(scooter.getScooterId(),endStop.getLatitude(),
                        endStop.getLongitude(),ScooterState.INACTIVE, endStop.getStopId(), request.kmTraveled())
        );

        trip.setStopEndId(endStop.getStopId());
        trip.setEndTime(Instant.now());
        trip.setKmTraveled(request.kmTraveled());

        FeeResponseDTO fee = feeService.getFeeById(request.feeId());

        if (fee == null) {
            throw  new EntityNotFoundException("Fee with id " + request.feeId() + " not found");
        }

        int pauseDuration = trip.getPauseCount();

        float totalPrice = TripCostCalculator.calculateTotalPrice(trip.getStartTime(), trip.getEndTime(),
                pauseDuration, fee.pricePerHour(), fee.extraHourFee());

        trip.setTotalPrice(totalPrice);

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO startPauseTrip(String tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        if (trip.getEndTime() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip is already finished.");
        }

        if(trip.getStartPause() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip is already paused.");
        }

        trip.setStartPause(Instant.now());
        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO endPauseTrip(String tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        if(trip.getStartPause() == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip is not paused.");
        }

        Instant startPause = trip.getStartPause();
        Instant endPause = Instant.now();
        int duration = (int) Duration.between(startPause, endPause).toMinutes();
        trip.setEndPause(endPause);
        trip.setPauseCount(duration +  trip.getPauseCount());

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public void deleteTrip(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));
        tripRepository.delete(trip);
    }

    public TripResponseDTO findTripById(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException( "Trip not found"));
        return TripResponseDTO.toDTO(trip);
    }

    public List<TripResponseDTO> findAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(TripResponseDTO::toDTO)
                .toList();
    }

    public InvoiceReportResponseDTO getTotalInvoice(int year, int startMonth, int endMonth) {
        InvoiceReportResponseDTO report = tripRepository.getInvoiceSummaryByYearAndMonthRange(year, startMonth, endMonth);
        if (report == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
        }
        return report;
    }

    //Report E
    public List<TripScooterUserUsageDTO> getScooterUserUsage(int monthStart, int monthEnd){
        return tripRepository.getScooterUserUsage(monthStart,monthEnd);
    }

    //Report H (two methods)
    public List<UserScooterPeriodUsageDTO> getUserUsagePeriod(Long userId, int year ,int monthStart, int monthEnd) {

        Instant start = LocalDate.of(year, monthStart, 1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        Instant end = LocalDate.of(year, monthEnd, YearMonth.of(year, monthEnd).lengthOfMonth())
                .atTime(23, 59, 59)
                .atZone(ZoneOffset.UTC)
                .toInstant();

        return tripRepository.getUsageByUsersAndPeriod(userId, start, end);
    }

    public List<UserScooterPeriodUsageDTO> getUsagePeriodForUsersByAccount(
            List<Long> userIds,
            int year,
            int monthStart,
            int monthEnd
    ) {
        Instant start = LocalDate.of(year, monthStart, 1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        Instant end = LocalDate.of(year, monthEnd, YearMonth.of(year, monthEnd).lengthOfMonth())
                .atTime(23, 59, 59)
                .atZone(ZoneOffset.UTC)
                .toInstant();

        return tripRepository.getUsagePeriodForUsersByAccount(userIds, start, end);
    }

    public TripStatsDTO getTripsStats(Long userId) {
        User user = userFeignClient.getUserById(userId);

        if (user == null) {
            throw new EntityNotFoundException("the user with the id " + userId + "not exist");
        }

        TripStatsDTO stats = tripRepository.getTripsStatsByUserId(userId);

        return new TripStatsDTO(
                user.getName(),
                stats.totalSpent(),
                stats.totalKm(),
                stats.scooterUsed(),
                stats.longestTrip(),
                stats.mostExpensiveTrip()
        );
    }



}
