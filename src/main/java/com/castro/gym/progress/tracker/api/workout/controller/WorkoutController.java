package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.domain.service.workout.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Workout", description = "Manage workout templates")
@RequestMapping("/api/v1/workouts")
@RestController
public class WorkoutController {
    private final WorkoutService workoutService;

    @Operation(summary = "Get all workouts of user")
    @GetMapping
    public List<WorkoutResponse> getWorkoutsByUser() {
        return workoutService.getWorkoutsByUser();
    }

    @Operation(summary = "Create a new workout")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(@Valid @RequestBody WorkoutRequest workout) {
        return workoutService.create(workout);
    }

    @Operation(summary = "Get workout by ID")
    @GetMapping("/{id}")
    public WorkoutResponse getWorkoutById(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @Operation(summary = "Update a workout")
    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutRequest updatedWorkout) {
        return workoutService.update(id, updatedWorkout);
    }

    @Operation(summary = "Delete a workout")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.delete(id);
    }
}