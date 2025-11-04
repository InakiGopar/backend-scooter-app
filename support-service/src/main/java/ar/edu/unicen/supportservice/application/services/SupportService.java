package ar.edu.unicen.supportservice.application.services;

import ar.edu.unicen.supportservice.application.repositories.SupportRepository;
import ar.edu.unicen.supportservice.domain.dtos.request.SupportRequestCreateDTO;
import ar.edu.unicen.supportservice.domain.dtos.request.SupportRequestUpdateDTO;
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

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;
    private final ScooterFeignClient scooterFeignClient;

    @Transactional
    public SupportResponseDTO create(SupportRequestCreateDTO request) {

        Scooter scooter = scooterFeignClient.getScooterById(request.scooterId());

        // check number 1
       if (scooter == null) {
           throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Scooter Not Found");
       }
        // check number 2
       if (scooter.getState() == ScooterState.ACTIVE || scooter.getState() == ScooterState.INMAINTENANCE) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Scooter cannot use");
       }
        // updated the scooter to the state IN MAINTENANCE
       scooterFeignClient.updateScooterStatus(request.scooterId(), ScooterState.INMAINTENANCE);

       // create the new support
        Support support = new Support();
        support.setScooterId(request.scooterId());
        support.setStartDate(request.startDate());
        support.setEndDate(request.endDate());


        supportRepository.save(support);

        return new SupportResponseDTO(
                support.getSupportId(),
                support.getScooterId(),
                support.getStartDate(),
                support.getEndDate()
        );

    }

    @Transactional
    public SupportResponseDTO update( Long supportId, SupportRequestUpdateDTO request ) {
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


        //updated the support
        support.setScooterId(request.scooterId());
        support.setStartDate(request.startDate());
        support.setEndDate(request.endDate());

        supportRepository.save(support);

        return new SupportResponseDTO(
                supportId,
                support.getScooterId(),
                support.getStartDate(),
                support.getEndDate()
        );
    }

    public void delete(Long supportId) {
        //check 1
        Support support = supportRepository.findById(supportId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Support Not Found")
        );

        //updated the scooter to the state INACTIVE because the support ended
        scooterFeignClient.updateScooterStatus(support.getScooterId(), ScooterState.INACTIVE);

        supportRepository.deleteById(supportId);
    }





}
