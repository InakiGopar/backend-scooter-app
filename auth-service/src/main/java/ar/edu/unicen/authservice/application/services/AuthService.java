package ar.edu.unicen.authservice.application.services;

import ar.edu.unicen.authservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.authservice.infrastructure.config.JwtUtils;
import ar.edu.unicen.authservice.infrastructure.feignClients.UserFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserFeignClient userFeignClient;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        email = email.trim().toLowerCase();

        UserResponseDTO user = userFeignClient.findByEmail(email);

        if(user == null){
            throw new EntityNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(password, user.password())) {
            throw new EntityNotFoundException("Invalid credentials");
        }

        return jwtUtils.generateToken(user.userId(), user.email(),user.role());
    }
}
