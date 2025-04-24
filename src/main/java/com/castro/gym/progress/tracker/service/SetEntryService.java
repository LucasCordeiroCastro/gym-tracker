package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.model.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.model.entity.log.SetEntry;
import com.castro.gym.progress.tracker.model.mapper.SetEntryMapper;
import com.castro.gym.progress.tracker.repository.SetEntryRepository;
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
        return setEntryRepository.findByExerciseLogIdOrderBySetOrderAsc(logId)
                .stream()
                .map(setEntryMapper::toResponse)
                .toList();
    }
}
