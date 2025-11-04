package ar.edu.unicen.scooterservice.application.services;

import ar.edu.unicen.scooterservice.application.repositories.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StopService {
    private final StopRepository stopRepository;
}
