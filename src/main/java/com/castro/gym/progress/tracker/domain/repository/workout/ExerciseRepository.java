package com.castro.gym.progress.tracker.domain.repository.workout;

import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroupsContaining(MuscleGroup muscleGroup);
}
