package ar.edu.unicen.scooterservice.application.services;

import ar.edu.unicen.scooterservice.application.repositories.ScooterRepository;
import ar.edu.unicen.scooterservice.domain.dtos.request.ScooterRequestDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterResponseDTO;
import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepository;

    @Transactional
    public ScooterResponseDTO createScooter(ScooterRequestDTO request){
        Scooter scooter = request.toEntity();

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }

    public ScooterResponseDTO getScooterById(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        return ScooterResponseDTO.toDTO(scooter);
    }

    @Transactional
    public ScooterResponseDTO updateScooter(Long scooterId, ScooterRequestDTO request) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        scooter.setLatitude(request.latitude());
        scooter.setLongitude(request.longitude());
        scooter.setState(request.state());

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }


    @Transactional
    public ScooterResponseDTO updateScooterStatus(Long scooterId, ScooterRequestDTO request) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        scooter.setState(request.state());

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }

    @Transactional
    public ScooterResponseDTO updateScooterStop( Long scooterId, ScooterRequestDTO request ) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(()-> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));

        scooter.setCurrentStop(request.currentStop());

        scooterRepository.save(scooter);

        return ScooterResponseDTO.toDTO(scooter);
    }

    public void deleteScooter(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new EntityNotFoundException("Scooter with id " + scooterId + " not found"));
        scooterRepository.delete(scooter);
    }
}
