package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.helper.TripCostCalculator;
import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.scooter.FeignScooterEndTripRequest;
import ar.edu.unicen.tripservice.domain.dtos.request.scooter.FeignScooterPatchRequest;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.fee.FeeResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.*;
import ar.edu.unicen.tripservice.domain.entities.Trip;
import ar.edu.unicen.tripservice.domain.model.scooter.Scooter;
import ar.edu.unicen.tripservice.domain.model.scooter.ScooterState;
import ar.edu.unicen.tripservice.domain.model.scooter.Stop;
import ar.edu.unicen.tripservice.domain.model.user.User;
import ar.edu.unicen.tripservice.infrastructure.feingClients.ScooterFeignClient;
import ar.edu.unicen.tripservice.infrastructure.feingClients.UserFeignClient;
import ar.edu.unicen.tripservice.infrastructure.feingClients.AccountFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final FeeService feeService;
    private final UserFeignClient userFeignClient;
    private final ScooterFeignClient scooterFeignClient;
    private final AccountFeignClient accountFeignClient;

    public List<ScooterUsageResponseDTO> findAllByKilometers(boolean withPause) {
        if(!withPause) {
            return tripRepository.findAllByKilometers();
        }
        return tripRepository.findAllByKilometersAndPause();
    }

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

        Stop endStop = scooterFeignClient.getStopById(request.stopEndId());

        if (endStop == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stop with id " + request.stopEndId() + " not found");
        }

        if (trip.getStartTime() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip has not been started.");
        }
        scooterFeignClient.updateScooterWhenTripEnd(
                request.scooterId(),
                new FeignScooterEndTripRequest(request.scooterId(),endStop.getLatitude(),
                        endStop.getLongitude(),ScooterState.INACTIVE, endStop.getStopId(), request.kmTraveled())
        );

        trip.setStopEndId(endStop.getStopId());
        trip.setEndTime(Instant.now());
        trip.setKmTraveled(request.kmTraveled());

        FeeResponseDTO fee = feeService.getFeeById(request.feeId());

        if (fee == null) {
            throw  new EntityNotFoundException("Fee with id " + request.feeId() + " not found");
        }

        //CHECK THIS, throws NullPointerException
        int pauseDuration = Duration.between(trip.getStartPause(), trip.getEndPause()).toMinutesPart();
        int totalPause = trip.getPauseCount() + pauseDuration;
        trip.setPauseCount(totalPause);

        float totalPrice = TripCostCalculator.calculateTotalPrice(trip.getStartTime(), trip.getEndTime(),
                pauseDuration, trip.getEndPause() ,fee.pricePerHour(), fee.extraHourFee());

        trip.setTotalPrice(totalPrice);

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO startPauseTrip(String tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));


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
        int duration = Duration.between(startPause, endPause).toMinutesPart();
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

    public List<TripScooterUserUsageDTO> getScooterUserUsage(int monthStart, int monthEnd){
        return tripRepository.getScooterUserUsage(monthStart,monthEnd);
    }

    /**
     * Obtiene el uso de monopatines para un usuario en un período; si accountId != null
     * agrega también los usuarios relacionados a esa cuenta.
     * @param userId usuario principal
     * @param start inicio como Instant
     * @param end fin como Instant
     * @param accountId opcional: si se proporciona, incluye usuarios de esa cuenta
     */
    public List<UserPeriodUsageResponseDTO> getUserUsagePeriod(Long userId, Instant start, Instant end, Long accountId){
        List<Long> userIds = new ArrayList<>();
        userIds.add(userId);

        if(accountId != null){
            List<AccountFeignClient.AccountUserResponseDTO> accountUsers = accountFeignClient.getAccountUsers(accountId);
            if(accountUsers != null){
                for(AccountFeignClient.AccountUserResponseDTO au : accountUsers){
                    if(!userIds.contains(au.userId())) userIds.add(au.userId());
                }
            }
        }

        return tripRepository.getUsageByUsersAndPeriod(userIds, start, end);
    }


}
