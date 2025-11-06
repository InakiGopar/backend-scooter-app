package ar.edu.unicen.supportservice.infrastructure.controllers;

import ar.edu.unicen.supportservice.application.services.SupportService;
import ar.edu.unicen.supportservice.domain.dtos.request.SupportRequestDTO;
import ar.edu.unicen.supportservice.domain.dtos.response.SupportResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;

    @PostMapping
    public ResponseEntity<SupportResponseDTO> create (@RequestBody SupportRequestDTO request) {
        SupportResponseDTO response = supportService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> update (@PathVariable Long id, @RequestBody SupportRequestDTO request) {
        SupportResponseDTO response = supportService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supportService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> get(@PathVariable Long id) {
        SupportResponseDTO response = supportService.findById(id);
        return ResponseEntity.ok(response);
    }

}
