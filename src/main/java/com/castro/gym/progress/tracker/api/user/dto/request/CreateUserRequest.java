package com.castro.gym.progress.tracker.api.user.dto.request;

import com.castro.gym.progress.tracker.domain.entity.user.GenderEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Name is required")
        String name,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "Gender is required")
        GenderEnum gender,

        @NotNull(message = "Height is required")
        Double height,

        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past")
        LocalDate birthDate
) {
}