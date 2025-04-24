package com.castro.gym.progress.tracker.model.dto.response;

import com.castro.gym.progress.tracker.model.entity.workout.MuscleGroupEnum;

import java.util.List;

public record ExerciseResponse(
        Long id,
        String name,
        List<MuscleGroupEnum> muscleGroups
) {
}
