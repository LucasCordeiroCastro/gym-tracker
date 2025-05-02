package com.castro.gym.progress.tracker.api.workout.dto.response;

import com.castro.gym.progress.tracker.domain.entity.log.SetDifficultyEnum;

public record SetEntryResponse(
        Long id,
        Integer reps,
        Double weight,
        Double volume,
        SetDifficultyEnum difficulty
) {
}
