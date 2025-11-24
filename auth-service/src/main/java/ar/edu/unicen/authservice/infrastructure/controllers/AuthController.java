package ar.edu.unicen.authservice.infrastructure.controllers;

import ar.edu.unicen.authservice.application.services.AuthService;
import ar.edu.unicen.authservice.domain.dtos.request.LoginRequest;
import ar.edu.unicen.authservice.domain.dtos.response.LoginResponse;
import ar.edu.unicen.authservice.domain.dtos.response.ValidateTokenResponse;
import ar.edu.unicen.authservice.domain.model.Role;
import ar.edu.unicen.authservice.infrastructure.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenResponse> validate(
            @RequestHeader("Authorization") String authHeader
    ) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(new ValidateTokenResponse(
                    false, null, null));
        }

        String token = authHeader.replace("Bearer ", "");

        boolean valid = jwtUtils.isTokenValid(token);

        if (!valid) {
            return ResponseEntity.ok(new ValidateTokenResponse(
                    false, null, null));
        }

        String email = jwtUtils.getEmail(token);
        String role  = jwtUtils.getRole(token);

        return ResponseEntity.ok(new ValidateTokenResponse(true, email, role));
    }

}
