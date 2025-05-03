package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.domain.entity.user.User;
import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseLogRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.WorkoutRepository;
import com.castro.gym.progress.tracker.domain.service.user.UserAuthorizationHelper;
import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.mapper.ExerciseLogMapper;
import com.castro.gym.progress.tracker.mapper.SetEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExerciseLogService {
    private final ExerciseLogRepository exerciseLogRepository;
    private final ExerciseLogMapper exerciseLogMapper;
    private final SetEntryMapper setEntryMapper;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final UserAuthorizationHelper userAuthorizationHelper;

    public ExerciseLogResponse findById(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return exerciseLogRepository.findByIdAndUserId(id, userId)
                .map(exerciseLogMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + id));
    }

    public List<ExerciseLogResponse> findLogsByUser() {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return exerciseLogRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(exerciseLogMapper::toResponse)
                .toList();
    }

    public List<ExerciseLogResponse> findLogsByUserAndExercise(Long exerciseId) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return exerciseLogRepository.findByUserIdAndExerciseIdOrderByDateDesc(userId, exerciseId)
                .stream()
                .map(exerciseLogMapper::toResponse)
                .toList();
    }

    public ExerciseLogResponse create(ExerciseLogRequest dto) {
        User user = userAuthorizationHelper.getAuthenticatedUser();
        ExerciseLog exerciseLog = exerciseLogMapper.toEntity(dto);

        exerciseLog.setUser(user);
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));

        if (dto.workoutId() != null) exerciseLog.setWorkout(fetchWorkout(dto.workoutId(), user.getId()));

        if (exerciseLog.getSets() != null) exerciseLog.getSets().forEach(set -> set.setExerciseLog(exerciseLog));

        return exerciseLogMapper.toResponse(exerciseLogRepository.save(exerciseLog));
    }

    public ExerciseLogResponse update(Long id, ExerciseLogRequest dto) {
        User user = userAuthorizationHelper.getAuthenticatedUser();
        ExerciseLog exerciseLog = exerciseLogRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + id));

        exerciseLogMapper.updateFromDto(dto, exerciseLog);

        exerciseLog.setUser(user);
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));
        if (dto.workoutId() != null)
            exerciseLog.setWorkout(fetchWorkout(dto.workoutId(), user.getId()));
        else exerciseLog.setWorkout(null);

        Map<Long, SetEntry> existingSetMap = exerciseLog.getSets().stream()
                .filter(s -> s.getId() != null)
                .collect(Collectors.toMap(SetEntry::getId, Function.identity()));

        exerciseLog.getSets().removeIf(existing ->
                dto.sets().stream().noneMatch(s -> s.id() != null && s.id().equals(existing.getId()))
        );

        for (SetEntryRequest setDto : dto.sets()) {
            if (setDto.id() != null && existingSetMap.containsKey(setDto.id())) {
                SetEntry existing = existingSetMap.get(setDto.id());
                setEntryMapper.updateFromDto(setDto, existing);
            } else {
                SetEntry newEntry = setEntryMapper.toEntity(setDto);
                newEntry.setExerciseLog(exerciseLog);
                exerciseLog.getSets().add(newEntry);
            }
        }

        return exerciseLogMapper.toResponse(exerciseLogRepository.save(exerciseLog));
    }

    private Exercise fetchExercise(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found: " + exerciseId));
    }

    private Workout fetchWorkout(Long workoutId, Long userId) {
        return workoutRepository.findByIdAndUserId(workoutId, userId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + workoutId));
    }

    public void delete(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        ExerciseLog exerciseLog = exerciseLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + id));

        exerciseLogRepository.delete(exerciseLog);
    }
}
