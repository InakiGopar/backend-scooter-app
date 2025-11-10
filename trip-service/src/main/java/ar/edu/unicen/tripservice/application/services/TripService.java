package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.FeeRepository;
import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.fee.FeeResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.ScooterUsageResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripResponseDTO;
import ar.edu.unicen.tripservice.domain.entities.Fee;
import ar.edu.unicen.tripservice.domain.entities.Trip;
import ar.edu.unicen.tripservice.domain.model.scooter.Scooter;
import ar.edu.unicen.tripservice.domain.model.scooter.ScooterState;
import ar.edu.unicen.tripservice.domain.model.scooter.Stop;
import ar.edu.unicen.tripservice.domain.model.user.User;
import ar.edu.unicen.tripservice.infrastructure.feingClients.ScooterFeignClient;
import ar.edu.unicen.tripservice.infrastructure.feingClients.StopFeignClient;
import ar.edu.unicen.tripservice.infrastructure.feingClients.UserFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final FeeService feeService;
    private final UserFeignClient userFeignClient;
    private final ScooterFeignClient scooterFeignClient;
    private final StopFeignClient stopFeignClient;

    public List<ScooterUsageResponseDTO> findAllByPause(String filter) {
        if(!filter.equals("true")) {
            return tripRepository.findAllByKilometers();
        }
        return tripRepository.findAllByPause();
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

        scooterFeignClient.updateScooterStatusAndStop(scooter.getScooterId(),
                new Scooter(scooter.getScooterId(), ScooterState.ACTIVE, null));

        Trip trip = request.toEntity();
        tripRepository.save(trip);
        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO endTrip(String tripId, TripRequestDTO request){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        Stop endStop = stopFeignClient.getStopById(request.stopEndId());

        if (endStop == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stop with id " + request.stopEndId() + " not found");
        }

        scooterFeignClient.updateScooter(request.scooterId(),
                new Scooter(request.scooterId(),endStop.getLatitude(),
                        endStop.getLongitude(),ScooterState.INACTIVE, endStop, request.kmTraveled()));

        trip.setStopEndId(endStop.getStopId());
        trip.setEndTime(request.endTime());
        trip.setKmTraveled(request.kmTraveled());

        float totalPrice = this.calculateTotalPrice(request.startTime(), request.endTime(),
                request.startPause(), request.endPause(), request.limitPauseMinutes(), request.feeId());

        trip.setTotalPrice(totalPrice);

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO startPauseTrip(String tripId, TripRequestDTO request){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));


        if(trip.getStartPause() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Trip is already paused.");
        }

        trip.setStartPause(request.startPause());
        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO endPauseTrip(String tripId, TripRequestDTO request){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        trip.setEndPause(request.endPause());

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public void delete(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));
        tripRepository.delete(trip);
    }

    public TripResponseDTO findById(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException( "Trip not found"));
        return TripResponseDTO.toDTO(trip);
    }

    public List<TripResponseDTO> findAll() {
        return tripRepository.findAll()
                .stream()
                .map(TripResponseDTO::toDTO)
                .toList();
    }



    //CAMBIAR ESTA LOGICA
    private float calculateTotalPrice(Instant startTime,Instant endTime, Instant startPause, Instant endPause, int limitPauseMinutes,String feeId) {
        FeeResponseDTO fee = feeService.getFeeById(feeId);
        float pricePerHr = fee.pricePerHour();

        int hours = Duration.between(startTime, endTime).toHoursPart();
        int pause = Duration.between(startPause, endPause).toMinutesPart();

        if(pause >= limitPauseMinutes){
            float extraPrice = fee.extraHourFee();
            int extraHours = Duration.between(endPause, endTime).toHoursPart();
            return (hours * pricePerHr) + (extraHours * extraPrice);
        }

        return (hours * pricePerHr);

    }


}
