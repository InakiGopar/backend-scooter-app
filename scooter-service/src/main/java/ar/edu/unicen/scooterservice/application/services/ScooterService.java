package ar.edu.unicen.scooterservice.application.services;

import ar.edu.unicen.scooterservice.application.repositories.ScooterRepository;
import ar.edu.unicen.scooterservice.application.repositories.StopRepository;
import ar.edu.unicen.scooterservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.scooterservice.domain.dtos.request.ScooterRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.request.ScooterRequestPatchDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterResponseDTO;
import ar.edu.unicen.scooterservice.domain.dtos.report.ScooterTripKMReportDTO;
import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.entities.Stop;
import ar.edu.unicen.scooterservice.domain.model.Trip;
import ar.edu.unicen.scooterservice.infrastructure.feingClients.TripFeignClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepository;
    private final StopRepository stopRepository;
    private final TripFeignClient tripFeignClient;

    @Transactional
    public ScooterResponseDTO createScooter(ScooterRequestDTO request){
        Stop currentStop = stopRepository.findById(request.currentStopId())
                .orElseThrow(() -> new EntityNotFoundException("Stop not found with id " + request.currentStopId()));

        Scooter scooter = request.toEntity(currentStop);

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }

    public ScooterResponseDTO findScooterById(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        return ScooterResponseDTO.toDTO(scooter);
    }

    @Transactional
    public ScooterResponseDTO updateScooter(Long scooterId, ScooterRequestDTO request) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        Stop currentStop = stopRepository.findById(request.currentStopId())
                .orElseThrow(() -> new EntityNotFoundException("Stop not found with id " + request.currentStopId()));

        scooter.setLatitude(request.latitude());
        scooter.setLongitude(request.longitude());
        scooter.setState(request.state());
        scooter.setCurrentStop(currentStop);

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }


    @Transactional
    public ScooterResponseDTO updateScooterStatus(Long scooterId, ScooterRequestPatchDTO request) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));


        scooter.setState(request.state());

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }

    public void deleteScooter(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));
        scooterRepository.delete(scooter);
    }

    //Report A
    public List<ScooterTripKMReportDTO> getScootersReportByKilometers(Boolean withPause){
        List<Trip> trips = tripFeignClient.findAllByKilometers(withPause);

        return trips.stream()
                .map(trip -> {
                    Scooter scooter = scooterRepository.findById(trip.getScooterId())
                            .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + trip.getScooterId() + " not found"));
                    return ScooterTripKMReportDTO.toDTO(scooter, trip);
                })
                .toList();
    }

    //Report C
    public List<ScooterTripKMReportDTO> getScootersReportByTravels(int year, int countTrips){
        List<Trip> trips = tripFeignClient.findAllByTravels(year, countTrips);

        return trips.stream()
                .map(trip -> {
                    Scooter scooter = scooterRepository.findById(trip.getScooterId())
                            .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + trip.getScooterId() + " not found"));
                    return ScooterTripKMReportDTO.toDTO(scooter, trip);
                })
                .toList();
    }


    //Report G
    public List<NearScooterReportDTO> getNearScooters(float latitude, float longitude) {
        return scooterRepository.getByLatitudeAndLongitude(latitude, longitude);
    }
}
