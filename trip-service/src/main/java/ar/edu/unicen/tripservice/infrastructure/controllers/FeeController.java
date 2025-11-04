package ar.edu.unicen.tripservice.infrastructure.controllers;

import ar.edu.unicen.tripservice.application.services.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fee")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;
}
