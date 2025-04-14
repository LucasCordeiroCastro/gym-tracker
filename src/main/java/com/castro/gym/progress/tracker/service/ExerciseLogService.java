package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import com.castro.gym.progress.tracker.repository.ExerciseLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseLogService {
    private final ExerciseLogRepository exerciseLogRepository;

    public List<ExerciseLog> findLogsByUserAndExercise(Long userId, Long exerciseId) {
        return exerciseLogRepository.findByUserIdAndExerciseIdOrderByDateDesc(userId, exerciseId);
    }

    public ExerciseLog save(ExerciseLog log) {
        return exerciseLogRepository.save(log);
    }
}
