package com.castro.gym.progress.tracker.api.workout.dto.response;

import com.castro.gym.progress.tracker.domain.enums.SetDifficulty;

public record SetEntryResponse(
        Long id,
        Integer reps,
        Double weight,
        Double volume,
        SetDifficulty difficulty
) {
}
