package com.castro.gym.progress.tracker.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "Name is required") String name,
        @Email(message = "Email must be valid") String email,
        String password,
        Double height,
        Double currentWeight,
        LocalDate birthDate
) { }