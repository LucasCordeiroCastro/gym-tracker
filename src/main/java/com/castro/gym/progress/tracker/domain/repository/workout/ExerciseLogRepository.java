package com.castro.gym.progress.tracker.domain.repository.workout;

import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByUserIdAndExerciseIdOrderByDateDesc(Long userId, Long exerciseId);
    List<ExerciseLog> findByUserIdOrderByDateDesc(Long userId);
    List<ExerciseLog> findByWorkoutIdAndDate(Long workoutId, LocalDate date);
}
