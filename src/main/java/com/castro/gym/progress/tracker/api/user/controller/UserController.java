package com.castro.gym.progress.tracker.api.user.controller;

import com.castro.gym.progress.tracker.api.user.dto.request.CreateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.request.UpdatePasswordRequest;
import com.castro.gym.progress.tracker.api.user.dto.request.UpdateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.response.UserResponse;
import com.castro.gym.progress.tracker.domain.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping
    public UserResponse getUserDetails() {
        return userService.getUser();
    }

    @PutMapping
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updatedUser) {
        return userService.updateUser(updatedUser);
    }

    @PutMapping("/password")
    public UserResponse updatePassword(@Valid @RequestBody UpdatePasswordRequest updatedPassword) {
        return userService.updatePassword(updatedPassword);
    }
}