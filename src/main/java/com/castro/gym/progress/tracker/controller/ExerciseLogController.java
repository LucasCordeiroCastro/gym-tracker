package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.service.ExerciseLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercise-logs")
public class ExerciseLogController {
    private final ExerciseLogService exerciseLogService;

    @GetMapping("/user/{userId}/exercise/{exerciseId}")
    public List<ExerciseLogResponse> getLogsByUserAndExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        return exerciseLogService.findLogsByUserAndExercise(userId, exerciseId);
    }

    @GetMapping
    public List<ExerciseLogResponse> getAllExerciseLogs() {
        return exerciseLogService.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseLogResponse getExerciseLogById(@PathVariable Long id) {
        return exerciseLogService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseLogResponse createExerciseLog(@Valid @RequestBody ExerciseLogRequest exerciseLogRequest) {
        return exerciseLogService.create(exerciseLogRequest);
    }

    @PutMapping("/{id}")
    public ExerciseLogResponse updateExerciseLog(@PathVariable Long id, @Valid @RequestBody ExerciseLogRequest updatedExerciseLog) {
        return exerciseLogService.update(id, updatedExerciseLog);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExerciseLog(@PathVariable Long id) {
        exerciseLogService.delete(id);
    }
}
