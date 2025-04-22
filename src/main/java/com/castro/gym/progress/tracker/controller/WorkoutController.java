package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.model.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import com.castro.gym.progress.tracker.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/workouts")
@RestController
public class WorkoutController {
    private final WorkoutService workoutService;

    @GetMapping("/user/{userId}")
    public List<WorkoutResponse> getWorkoutsByUser(@PathVariable Long userId) {
        return workoutService.getWorkoutsByUser(userId);
    }

    @PostMapping
    public WorkoutResponse createWorkout(@Valid @RequestBody WorkoutRequest workout) {
        return workoutService.create(workout);
    }

    @GetMapping("/{id}")
    public WorkoutResponse getWorkout(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutRequest updated) {
        return workoutService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.delete(id);
    }
}