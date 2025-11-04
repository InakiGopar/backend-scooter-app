package ar.edu.unicen.scooterservice.infrastructure.controllers;

import ar.edu.unicen.scooterservice.application.services.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stop")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;
}
