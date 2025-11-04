package ar.edu.unicen.scooterservice.application.services;

import ar.edu.unicen.scooterservice.application.repositories.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepository;
}
