package com.castro.gym.progress.tracker.model.dto.request;

import com.castro.gym.progress.tracker.model.entity.workout.MuscleGroupEnum;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ExerciseRequest(
        @NotBlank(message = "Exercise name cannot be blank") String name,
        List<MuscleGroupEnum> muscleGroups
) {
}
