package com.castro.gym.progress.tracker.api.workout.dto.request;

import com.castro.gym.progress.tracker.domain.enums.SetDifficulty;
import jakarta.validation.constraints.NotNull;

public record SetEntryRequest(
        Long id,
        @NotNull(message = "Reps count cannot be null") Integer reps,
        @NotNull(message = "Weight number cannot be null") Double weight,
        SetDifficulty difficulty
) {
}
