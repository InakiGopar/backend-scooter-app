package ar.edu.unicen.supportservice.infrastructure.controllers;

import ar.edu.unicen.supportservice.application.services.SupportService;
import ar.edu.unicen.supportservice.domain.dtos.request.SupportRequestDTO;
import ar.edu.unicen.supportservice.domain.dtos.response.SupportResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;

    @PostMapping
    public ResponseEntity<SupportResponseDTO> createSupport (@RequestBody SupportRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supportService.createSupport(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> updateSupport (@PathVariable Long id, @RequestBody SupportRequestDTO request) {
        return ResponseEntity.ok(supportService.updateSupport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupport(@PathVariable Long id) {
        supportService.deleteSupport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportResponseDTO> findSupportById(@PathVariable Long id) {
        return ResponseEntity.ok(supportService.findSupportById(id));
    }

}
