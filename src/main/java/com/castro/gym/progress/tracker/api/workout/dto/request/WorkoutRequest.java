package com.castro.gym.progress.tracker.api.workout.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record WorkoutRequest(
        @NotBlank(message = "Name is required") String name,
        @Nullable List<Long> exerciseIds,
        String description
) {
}
