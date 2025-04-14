package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.entity.log.SetEntry;
import com.castro.gym.progress.tracker.repository.SetEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SetEntryService {
    private final SetEntryRepository setEntryRepository;

    public List<SetEntry> findByExerciseLog(Long logId) {
        return setEntryRepository.findByExerciseLogIdOrderBySetOrderAsc(logId);
    }

    public SetEntry save(SetEntry entry) {
        return setEntryRepository.save(entry);
    }
}
