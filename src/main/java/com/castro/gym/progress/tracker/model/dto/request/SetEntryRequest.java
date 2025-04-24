package com.castro.gym.progress.tracker.model.dto.request;

import com.castro.gym.progress.tracker.model.entity.log.SetDifficultyEnum;
import jakarta.validation.constraints.NotNull;

public record SetEntryRequest(
        @NotNull(message = "Reps count cannot be null") Integer reps,
        @NotNull(message = "Weight number cannot be null") Double weight,
        SetDifficultyEnum difficulty
) {
}
