package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
}
