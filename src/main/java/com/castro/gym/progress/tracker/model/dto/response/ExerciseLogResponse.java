package com.castro.gym.progress.tracker.model.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ExerciseLogResponse(
        Long id,
        LocalDate date,
        String comment,
        Long userId,
        String userName,
        Long exerciseId,
        String exerciseName,
        Long workoutId,
        String workoutName,
        List<SetEntryResponse> sets,
        Double totalVolume
) {
}
