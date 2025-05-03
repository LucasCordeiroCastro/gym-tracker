package com.castro.gym.progress.tracker.api.workout.dto.request;

import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ExerciseRequest(
        @NotBlank(message = "Exercise name cannot be blank") String name,
        List<MuscleGroup> muscleGroups
) {
}
