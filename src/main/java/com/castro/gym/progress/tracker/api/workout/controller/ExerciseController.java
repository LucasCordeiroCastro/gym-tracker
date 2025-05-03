package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import com.castro.gym.progress.tracker.domain.service.workout.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseResponse> getExercises(@RequestParam(required = false) MuscleGroup muscleGroup) {
        if (muscleGroup != null)
            return exerciseService.findByMuscleGroup(muscleGroup);

        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    //TODO: ADMIN ONLY
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(@Valid @RequestBody ExerciseRequest exerciseRequest) {
        return exerciseService.create(exerciseRequest);
    }

    //TODO: ADMIN ONLY
    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseRequest updatedExercise) {
        return exerciseService.update(id, updatedExercise);
    }

    //TODO: ADMIN ONLY
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.delete(id);
    }
}
