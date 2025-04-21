package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.UserRequest;
import com.castro.gym.progress.tracker.model.dto.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}