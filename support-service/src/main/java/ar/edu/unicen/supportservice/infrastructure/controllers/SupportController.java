package ar.edu.unicen.supportservice.infrastructure.controllers;

import ar.edu.unicen.supportservice.application.services.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;
}
