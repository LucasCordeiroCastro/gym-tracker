package com.castro.gym.progress.tracker.api.workout.dto.response;

import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;

import java.util.List;

public record ExerciseResponse(
        Long id,
        String name,
        List<MuscleGroup> muscleGroups
) {
}
