package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.log.SetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetEntryRepository extends JpaRepository<SetEntry, Long> {
    List<SetEntry> findByExerciseLogIdOrderBySetOrderAsc(Long exerciseLogId);
}
