package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import com.castro.gym.progress.tracker.domain.service.AbstractCrudService;
import com.castro.gym.progress.tracker.mapper.SetEntryMapper;
import com.castro.gym.progress.tracker.domain.repository.workout.SetEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetEntryService extends AbstractCrudService<
        SetEntry,
        Long,
        SetEntryRequest,
        SetEntryResponse
        > {

    private final SetEntryRepository setEntryRepository;
    private final SetEntryMapper setEntryMapper;

    public SetEntryService(SetEntryRepository setEntryRepository, SetEntryMapper setEntryMapper) {
        super(setEntryRepository, setEntryMapper::toEntity, setEntryMapper::toResponse, setEntryMapper::updateFromDto);
        this.setEntryRepository = setEntryRepository;
        this.setEntryMapper = setEntryMapper;
    }

    public List<SetEntryResponse> findByExerciseLog(Long logId) {
        return setEntryRepository.findByExerciseLogId(logId)
                .stream()
                .map(setEntryMapper::toResponse)
                .toList();
    }
}
