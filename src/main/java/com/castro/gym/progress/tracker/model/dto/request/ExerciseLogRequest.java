package com.castro.gym.progress.tracker.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record ExerciseLogRequest(
        @NotNull(message = "User ID cannot be null") Long userId,
        @NotNull(message = "Exercise ID cannot be null") Long exerciseId,
        Long workoutId,
        @NotNull(message = "Date cannot be null") LocalDate date,
        String comment,
        @NotEmpty(message = "Sets cannot be empty") List<SetEntryRequest> sets
) {
}
