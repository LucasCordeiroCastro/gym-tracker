package com.castro.gym.progress.tracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @NotBlank(message = "Old password is required") String oldPassword,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        @NotBlank(message = "New password is required") String newPassword
) {}
