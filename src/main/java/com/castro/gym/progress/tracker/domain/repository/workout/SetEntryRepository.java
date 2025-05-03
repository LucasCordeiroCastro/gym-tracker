package com.castro.gym.progress.tracker.domain.repository.workout;

import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SetEntryRepository extends JpaRepository<SetEntry, Long> {
    List<SetEntry> findByExerciseLogId(Long exerciseLogId);
    Optional<SetEntry> findByIdAndExerciseLogId(Long id, Long exerciseLogId);
}
