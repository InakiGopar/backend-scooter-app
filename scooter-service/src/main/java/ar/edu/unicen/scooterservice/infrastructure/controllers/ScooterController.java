package ar.edu.unicen.scooterservice.infrastructure.controllers;

import ar.edu.unicen.scooterservice.application.services.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scooter")
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;
}
