package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
}
