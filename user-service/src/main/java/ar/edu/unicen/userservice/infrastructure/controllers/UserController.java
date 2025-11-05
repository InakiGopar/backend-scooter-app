package ar.edu.unicen.userservice.infrastructure.controllers;

import ar.edu.unicen.userservice.application.services.UserService;
import ar.edu.unicen.userservice.domain.dtos.request.UserCreateRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.request.UserUpdateRequestDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateRequestDTO request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.createUser(request));
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserUpdateRequestDTO request){
    return null;
    }
}
