package ar.edu.unicen.scooterservice.application.services;

import ar.edu.unicen.scooterservice.application.repositories.StopRepository;
import ar.edu.unicen.scooterservice.domain.dtos.request.StopRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.StopResponseDTO;
import ar.edu.unicen.scooterservice.domain.entities.Stop;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StopService {
    private final StopRepository stopRepository;

    @Transactional
    public StopResponseDTO createStop(StopRequestDTO request) {
        Stop stop = request.toEntity();

        stopRepository.save(stop);

        return StopResponseDTO.toDTO(stop);
    }

    @Transactional
    public StopResponseDTO updateStop( Long stopId, StopRequestDTO request) {
        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(()-> new EntityNotFoundException("Stop not found with id " + request.stopId()));

        stop.setLatitude(request.latitude());
        stop.setLongitude(request.longitude());

        stopRepository.save(stop);

        return StopResponseDTO.toDTO(stop);
    }

    public void deleteStop(Long id) {
        Stop stop = stopRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Stop not found with id " + id));

        stopRepository.delete(stop);
    }

    public StopResponseDTO findStopById(Long stopId) {
        Stop stop = stopRepository.findById(stopId).orElseThrow(
                ()-> new EntityNotFoundException("Stop not found with id " + stopId)
        );
        return StopResponseDTO.toDTO(stop);
    }


}
