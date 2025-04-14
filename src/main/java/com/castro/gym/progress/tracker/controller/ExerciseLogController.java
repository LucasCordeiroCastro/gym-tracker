package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import com.castro.gym.progress.tracker.service.ExerciseLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercise-logs")
public class ExerciseLogController {
    private final ExerciseLogService exerciseLogService;

    @GetMapping("/user/{userId}/exercise/{exerciseId}")
    public List<ExerciseLog> getLogsByUserAndExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        return exerciseLogService.findLogsByUserAndExercise(userId, exerciseId);
    }

    @PostMapping
    public ExerciseLog createExerciseLog(@RequestBody ExerciseLog log) {
        return exerciseLogService.save(log);
    }
}
