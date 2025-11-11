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
    public ResponseEntity<SupportResponseDTO> createSupport (@RequestBody SupportRequestDTO request) {
        SupportResponseDTO response = supportService.createSupport(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> updateSupport (@PathVariable Long id, @RequestBody SupportRequestDTO request) {
        SupportResponseDTO response = supportService.updateSupport(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupport(@PathVariable Long id) {
        supportService.deleteSupport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> findSupportById(@PathVariable Long id) {
        SupportResponseDTO response = supportService.findSupportById(id);
        return ResponseEntity.ok(response);
    }

}
