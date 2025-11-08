package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final UserFeignClient userFeignClient;
    private final ScooterFeignClient scooterFeignClient;
    private final StopFeignClient stopFeignClient;

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
                        endStop.getLongitude(),ScooterState.INACTIVE, endStop));

        trip.setStopEndId(endStop.getStopId());
        trip.setTripHours(request.tripHours());
        trip.setPause(request.pause());
        trip.setKmTraveled(request.kmTraveled());
        trip.setTotalPrice(request.totalPrice());

        tripRepository.save(trip);

        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO togglePauseTrip(String tripId, TripRequestDTO request){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip: " + tripId + " not found"));

        trip.setPause(request.pause());
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


    /*
    private float calculateTotalPrice(int kmTraveled, int pause, String feeId) {
        Fee fee = getFeeById(feeId);
        if (fee != null) {
            float pricePerKm = fee.getPricePerKm();
            float pricePerMinutePause = fee.getPricePerMinutePause();
            return (kmTraveled * pricePerKm) + (pause * pricePerMinutePause);
        }
        return 0;
    }
     */
}
