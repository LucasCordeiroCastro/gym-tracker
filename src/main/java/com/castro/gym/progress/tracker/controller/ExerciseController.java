package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {
    private final ExerciseService service;

    @GetMapping
    public List<Exercise> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return service.save(exercise);
    }
}
