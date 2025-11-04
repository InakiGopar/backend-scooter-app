package ar.edu.unicen.supportservice.application.services;

import ar.edu.unicen.supportservice.application.repositories.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;
}
