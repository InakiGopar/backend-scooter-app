package ar.edu.unicen.userservice.application.services;

import ar.edu.unicen.userservice.application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
