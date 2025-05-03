package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseLogRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.SetEntryRepository;
import com.castro.gym.progress.tracker.domain.service.user.UserAuthorizationHelper;
import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.mapper.SetEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SetEntryService {

    private final SetEntryRepository setEntryRepository;
    private final SetEntryMapper setEntryMapper;
    private final UserAuthorizationHelper userAuthorizationHelper;
    private final ExerciseLogRepository exerciseLogRepository;

    public List<SetEntryResponse> findAllByExerciseLogId(Long exerciseLogId) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + exerciseLogId));

        userAuthorizationHelper.checkOwnership(exerciseLog.getUser().getId(), "ExerciseLog");

        return setEntryRepository.findByExerciseLogId(exerciseLogId)
                .stream()
                .map(setEntryMapper::toResponse)
                .toList();
    }

    public SetEntryResponse findById(Long id, Long exerciseLogId) {
        SetEntry setEntry = setEntryRepository.findByIdAndExerciseLogId(id, exerciseLogId)
                .orElseThrow(() -> new NotFoundException("Set entry not found"));

        userAuthorizationHelper.checkOwnership(setEntry.getExerciseLog().getUser().getId(), "SetEntry");
        return setEntryMapper.toResponse(setEntry);
    }

    public SetEntryResponse create(Long exerciseLogId, SetEntryRequest setEntryRequest) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + exerciseLogId));

        userAuthorizationHelper.checkOwnership(exerciseLog.getUser().getId(), "ExerciseLog");

        SetEntry newSetEntry = setEntryMapper.toEntity(setEntryRequest);
        newSetEntry.setExerciseLog(exerciseLog);

        return setEntryMapper.toResponse(setEntryRepository.save(newSetEntry));
    }

    public SetEntryResponse update(Long id, Long exerciseLogId, SetEntryRequest updatedSetEntry) {
        SetEntry setEntry = setEntryRepository.findByIdAndExerciseLogId(id, exerciseLogId)
                .orElseThrow(() -> new NotFoundException("Set entry not found"));

        userAuthorizationHelper.checkOwnership(setEntry.getExerciseLog().getUser().getId(), "SetEntry");

        setEntryMapper.updateFromDto(updatedSetEntry, setEntry);
        return setEntryMapper.toResponse(setEntryRepository.save(setEntry));
    }

    public void delete(Long id, Long exerciseLogId) {
        SetEntry setEntry = setEntryRepository.findByIdAndExerciseLogId(id, exerciseLogId)
                .orElseThrow(() -> new NotFoundException("Set entry not found"));

        userAuthorizationHelper.checkOwnership(setEntry.getExerciseLog().getUser().getId(), "SetEntry");

        setEntryRepository.delete(setEntry);
    }
}
