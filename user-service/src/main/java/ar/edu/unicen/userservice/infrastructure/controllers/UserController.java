package ar.edu.unicen.userservice.infrastructure.controllers;

import ar.edu.unicen.userservice.application.services.UserService;
import ar.edu.unicen.userservice.domain.dtos.request.UserRequestDTO;
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
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId, @RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
