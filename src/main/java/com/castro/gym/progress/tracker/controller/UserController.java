package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.UserRequest;
import com.castro.gym.progress.tracker.model.dto.response.UserResponse;
import com.castro.gym.progress.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest updatedUser) {
        return userService.update(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}