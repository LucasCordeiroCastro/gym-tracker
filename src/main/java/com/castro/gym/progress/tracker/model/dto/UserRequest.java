package com.castro.gym.progress.tracker.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank String name,
        @Email String email,
        String password,
        Double height,
        Double currentWeight,
        LocalDate birthDate
) { }