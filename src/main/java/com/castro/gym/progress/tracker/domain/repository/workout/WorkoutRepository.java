package com.castro.gym.progress.tracker.domain.repository.workout;

import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
}
