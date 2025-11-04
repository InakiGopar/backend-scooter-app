package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeService {
    private final FeeRepository feeRepository;
}
