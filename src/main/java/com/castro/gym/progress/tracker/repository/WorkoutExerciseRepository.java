package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.workout.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutIdOrderByExerciseOrderAsc(Long workoutId);
}
