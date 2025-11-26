package ar.edu.unicen.supportservice.application.services;

import ar.edu.unicen.supportservice.application.repositories.SupportRepository;
import ar.edu.unicen.supportservice.domain.dtos.request.SupportRequestDTO;
import ar.edu.unicen.supportservice.domain.dtos.response.SupportResponseDTO;
import ar.edu.unicen.supportservice.domain.entities.Support;
import ar.edu.unicen.supportservice.domain.model.Scooter;
import ar.edu.unicen.supportservice.domain.model.ScooterState;
import ar.edu.unicen.supportservice.infrastructure.feignClients.ScooterFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;
    private final ScooterFeignClient scooterFeignClient;

    @Transactional
    public SupportResponseDTO createSupport(SupportRequestDTO request) {

        // check number 1
        Scooter scooter = Objects.requireNonNull(scooterFeignClient.getScooterById(request.scooterId()),
                "Scooter with id " + request.scooterId() + " not found");

        // check number 3
        if (scooter.getState().equals(ScooterState.MAINTENANCE)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Scooter with id " + request.scooterId() +  " is already on maintenance");
        }

        // check number 2
       if (scooter.getState().equals(ScooterState.ACTIVE)) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Scooter cannot use");
       }
        // updated the scooter to the state MAINTENANCE
       scooterFeignClient.updateScooterState(request.scooterId(), ScooterState.MAINTENANCE);

       // persist the new support
        Support support = request.toEntity();
        supportRepository.save(support);

        return SupportResponseDTO.toDTO(support);

    }

    @Transactional
    public SupportResponseDTO updateSupport ( Long supportId, SupportRequestDTO request ) {
        Scooter scooter = scooterFeignClient.getScooterById(request.scooterId());

        // check number 1
        if (scooter == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Scooter Not Found");
        }

        // check number 2
        if (scooter.getState() == ScooterState.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Scooter cannot use");
        }

        // check number 3
        Support support = supportRepository.findById(supportId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Support Not Found"));

        support.setScooterId(request.scooterId());
        support.setStartDate(request.startDate());
        support.setEndDate(request.endDate());

        supportRepository.save(support);

        return SupportResponseDTO.toDTO(support);
    }

    public void deleteSupport(Long supportId) {
        //check 1
        Support support = supportRepository.findById(supportId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Support Not Found")
        );

        //updated the scooter to the state INACTIVE because the support ended
        scooterFeignClient.updateScooterState(support.getScooterId(), ScooterState.INACTIVE);

        supportRepository.deleteById(supportId);
    }


    public SupportResponseDTO findSupportById(Long supportId) {
        Support support = supportRepository.findById(supportId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Support Not Found")
        );
        return SupportResponseDTO.toDTO(support);
    }


}
