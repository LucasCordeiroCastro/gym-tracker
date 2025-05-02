package com.castro.gym.progress.tracker.api.user.dto.request;

import com.castro.gym.progress.tracker.domain.entity.user.GenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UpdateUserRequest(
        @Email(message = "Email must be valid")
        String email,

        String name,

        GenderEnum gender,

        Double height,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate
) {
}
