package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.service.UserAuthorizationHelper;
import com.castro.gym.progress.tracker.service.ExerciseLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercise-logs")
public class ExerciseLogController {
    private final ExerciseLogService exerciseLogService;
    private final UserAuthorizationHelper userAuthorizationHelper;

    @GetMapping("/user/{userId}/exercise/{exerciseId}")
    public List<ExerciseLogResponse> getLogsByUserAndExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        return exerciseLogService.findLogsByUserAndExercise(userId, exerciseId);
    }

    @GetMapping("/user")
    public List<ExerciseLogResponse> getAllLogsOfUser() {
        return exerciseLogService.findLogsByUser(userAuthorizationHelper.getAuthenticatedUserId());
    }

    @GetMapping("/{id}")
    public ExerciseLogResponse getExerciseLogById(@PathVariable Long id) {
        ExerciseLogResponse logResponse = exerciseLogService.findById(id);
        if (logResponse.userId().equals(userAuthorizationHelper.getAuthenticatedUserId()))
            return logResponse;
        else
            throw new AccessDeniedException("You do not have access to this log");
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

    //ADMIN ONLY
    @GetMapping
    public List<ExerciseLogResponse> getAllExerciseLogs() {
        return exerciseLogService.findAll();
    }
}
