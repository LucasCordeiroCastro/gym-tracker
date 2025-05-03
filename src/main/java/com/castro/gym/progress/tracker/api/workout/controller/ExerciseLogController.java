package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.domain.service.workout.ExerciseLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "ExerciseLog", description = "Manage exercise logs templates")
@RequestMapping("/api/v1/exercise-logs")
@RestController
public class ExerciseLogController {
    private final ExerciseLogService exerciseLogService;

    @Operation(summary = "Get all exercise logs of user")
    @GetMapping
    public List<ExerciseLogResponse> getUserExerciseLogs(@RequestParam(required = false) Long exerciseId) {
        if (exerciseId != null)
            return exerciseLogService.findLogsByUserAndExercise(exerciseId);

        return exerciseLogService.findLogsByUser();
    }

    @Operation(summary = "Get exercise log by ID")
    @GetMapping("/{id}")
    public ExerciseLogResponse getExerciseLogById(@PathVariable Long id) {
        return exerciseLogService.findById(id);
    }

    @Operation(summary = "Create a new exercise log")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseLogResponse createExerciseLog(@Valid @RequestBody ExerciseLogRequest exerciseLogRequest) {
        return exerciseLogService.create(exerciseLogRequest);
    }

    @Operation(summary = "Update an exercise log")
    @PutMapping("/{id}")
    public ExerciseLogResponse updateExerciseLog(@PathVariable Long id, @Valid @RequestBody ExerciseLogRequest updatedExerciseLog) {
        return exerciseLogService.update(id, updatedExerciseLog);
    }

    @Operation(summary = "Delete an exercise log")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExerciseLog(@PathVariable Long id) {
        exerciseLogService.delete(id);
    }
}
