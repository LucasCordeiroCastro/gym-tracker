package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.domain.service.workout.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/workouts")
@RestController
public class WorkoutController {
    private final WorkoutService workoutService;

    @GetMapping
    public List<WorkoutResponse> getWorkoutsByUser() {
        return workoutService.getWorkoutsByUser();
    }

    @PostMapping
    public WorkoutResponse createWorkout(@Valid @RequestBody WorkoutRequest workout) {
        return workoutService.create(workout);
    }

    @GetMapping("/{id}")
    public WorkoutResponse getWorkoutById(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutRequest updatedWorkout) {
        return workoutService.update(id, updatedWorkout);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.delete(id);
    }
}