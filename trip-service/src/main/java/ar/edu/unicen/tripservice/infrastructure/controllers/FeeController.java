package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.FeeService;
import ar.edu.unicen.tripservice.application.services.TripService;
import ar.edu.unicen.tripservice.domain.dtos.request.trip.TripRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fee")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;
}

