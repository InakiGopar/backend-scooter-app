package ar.edu.unicen.userservice.application.services;

import ar.edu.unicen.userservice.application.repositories.UserRepository;
import ar.edu.unicen.userservice.domain.dtos.request.*;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request){
        User user = request.toEntity();

        userRepository.save(user);

        return UserResponseDTO.toDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long userId, UserRequestDTO request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

        userRepository.save(request.toEntity());

        return UserResponseDTO.toDTO(user);
    }

    public UserResponseDTO getUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

        return UserResponseDTO.toDTO(user);
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }


}
