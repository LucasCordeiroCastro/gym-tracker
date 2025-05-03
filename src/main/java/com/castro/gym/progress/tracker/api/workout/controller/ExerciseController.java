package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import com.castro.gym.progress.tracker.domain.service.workout.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Exercises", description = "Manage exercise templates")
@RequestMapping("/api/v1/exercises")
@RestController
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Operation(summary = "Get all exercises")
    @GetMapping
    public List<ExerciseResponse> getExercises(@RequestParam(required = false) MuscleGroup muscleGroup) {
        if (muscleGroup != null)
            return exerciseService.findByMuscleGroup(muscleGroup);

        return exerciseService.findAll();
    }

    @Operation(summary = "Get exercise by ID")
    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @Operation(summary = "Create a new exercise (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(@Valid @RequestBody ExerciseRequest exerciseRequest) {
        return exerciseService.create(exerciseRequest);
    }

    @Operation(summary = "Update an exercise (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseRequest updatedExercise) {
        return exerciseService.update(id, updatedExercise);
    }

    @Operation(summary = "Delete an exercise (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.delete(id);
    }
}
