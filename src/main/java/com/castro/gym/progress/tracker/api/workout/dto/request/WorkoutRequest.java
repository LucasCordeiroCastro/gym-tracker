package com.castro.gym.progress.tracker.api.workout.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WorkoutRequest(
        @NotBlank(message = "Name is required") String name,
        @NotNull(message = "User ID is required") Long userId,
        @Nullable List<Long> exerciseIds,
        String description
) {
}
