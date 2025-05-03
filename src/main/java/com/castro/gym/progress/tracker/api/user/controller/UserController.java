package com.castro.gym.progress.tracker.api.user.controller;

import com.castro.gym.progress.tracker.api.user.dto.request.CreateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.request.UpdatePasswordRequest;
import com.castro.gym.progress.tracker.api.user.dto.request.UpdateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.response.UserResponse;
import com.castro.gym.progress.tracker.domain.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "User", description = "Manage user templates")
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "Create a new user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @Operation(summary = "Get user details")
    @GetMapping("/me")
    public UserResponse getUserDetails() {
        return userService.getUser();
    }

    @Operation(summary = "Update user details")
    @PutMapping
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updatedUser) {
        return userService.updateUser(updatedUser);
    }

    @Operation(summary = "Update user password")
    @PutMapping("/password")
    public UserResponse updatePassword(@Valid @RequestBody UpdatePasswordRequest updatedPassword) {
        return userService.updatePassword(updatedPassword);
    }

    @Operation(summary = "Get all users (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Delete user by ID (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Assign role to user (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/roles/{role}")
    public void assignRole(@PathVariable Long id, @PathVariable String role) {
        userService.addRole(id, role);
    }
}