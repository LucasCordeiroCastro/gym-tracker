package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.service.ExerciseService;
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
    public List<ExerciseResponse> getAllExercises() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(@Valid @RequestBody ExerciseRequest exerciseRequest) {
        return exerciseService.create(exerciseRequest);
    }

    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseRequest updatedExercise) {
        return exerciseService.update(id, updatedExercise);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.delete(id);
    }
}
