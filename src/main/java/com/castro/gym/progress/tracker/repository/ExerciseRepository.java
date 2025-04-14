package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(String muscleGroup);
}
