package com.castro.gym.progress.tracker.model.dto.response;

import com.castro.gym.progress.tracker.model.entity.log.SetDifficultyEnum;

public record SetEntryResponse(
        Long id,
        Integer reps,
        Double weight,
        Double volume,
        SetDifficultyEnum difficulty
) {
}
