package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripResponseDTO;
import ar.edu.unicen.tripservice.domain.entities.Trip;
import ar.edu.unicen.tripservice.domain.model.user.User;
import ar.edu.unicen.tripservice.infrastructure.feingClients.UserFeignClient;
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

    public TripResponseDTO create(TripRequestDTO request) {
        User user = userFeignClient.getUserById(request.userId());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + request.userId() + " not found");
        }

        if (request.startDate().after(request.endDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date");
        }

        Trip trip = request.toEntity();
        tripRepository.save(trip);
        return TripResponseDTO.toDTO(trip);
    }

    public TripResponseDTO update(String tripId, TripRequestDTO request) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        trip.setUserId(request.userId());
        trip.setScooterId(request.scooterId());
        trip.setStopSartId(request.stopSartId());
        trip.setStopEndId(request.stopEndId());
        trip.setDate(request.date());
        trip.setStartDate(request.startDate());
        trip.setEndDate(request.endDate());
        trip.setKmTraveled(request.kmTraveled());
        trip.setPause(request.pause());
        trip.setTotalPrice(request.totalPrice());


        tripRepository.save(trip);
        return TripResponseDTO.toDTO(trip);
    }

    public void delete(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));
        tripRepository.delete(trip);
    }

    public TripResponseDTO findById(String tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));
        return TripResponseDTO.toDTO(trip);
    }

    public List<TripResponseDTO> findAll() {
        return tripRepository.findAll()
                .stream()
                .map(TripResponseDTO::toDTO)
                .toList();
    }
}
