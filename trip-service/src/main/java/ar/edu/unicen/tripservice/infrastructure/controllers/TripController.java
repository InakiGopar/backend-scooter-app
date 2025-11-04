package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
}
