package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.domain.service.workout.ExerciseLogService;
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

    @GetMapping
    public List<ExerciseLogResponse> getUserExerciseLogs(@RequestParam(required = false) Long exerciseId) {
        if (exerciseId != null)
            return exerciseLogService.findLogsByUserAndExercise(exerciseId);

        return exerciseLogService.findLogsByUser();
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
