package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByUserIdAndExerciseIdOrderByDateDesc(Long userId, Long exerciseId);
    List<ExerciseLog> findByWorkoutIdAndDate(Long workoutId, LocalDate date);
}
