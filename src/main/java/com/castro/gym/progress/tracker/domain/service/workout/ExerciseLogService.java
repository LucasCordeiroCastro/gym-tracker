package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.domain.service.AbstractCrudService;
import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import com.castro.gym.progress.tracker.domain.entity.user.User;
import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import com.castro.gym.progress.tracker.mapper.ExerciseLogMapper;
import com.castro.gym.progress.tracker.mapper.SetEntryMapper;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseLogRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseRepository;
import com.castro.gym.progress.tracker.domain.repository.user.UserRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExerciseLogService extends AbstractCrudService<
        ExerciseLog,
        Long,
        ExerciseLogRequest,
        ExerciseLogResponse
        > {

    private final ExerciseLogRepository exerciseLogRepository;
    private final ExerciseLogMapper exerciseLogMapper;
    private final SetEntryMapper setEntryMapper;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public ExerciseLogService(ExerciseLogRepository exerciseLogRepository, ExerciseLogMapper exerciseLogMapper, SetEntryMapper setEntryMapper,
                              UserRepository userRepository, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        super(exerciseLogRepository, exerciseLogMapper::toEntity, exerciseLogMapper::toResponse, exerciseLogMapper::updateFromDto);
        this.exerciseLogRepository = exerciseLogRepository;
        this.exerciseLogMapper = exerciseLogMapper;
        this.setEntryMapper = setEntryMapper;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<ExerciseLogResponse> findLogsByUserAndExercise(Long userId, Long exerciseId) {
        return exerciseLogRepository.findByUserIdAndExerciseIdOrderByDateDesc(userId, exerciseId)
                .stream()
                .map(exerciseLogMapper::toResponse)
                .toList();
    }

    public List<ExerciseLogResponse> findLogsByUser(Long userId) {
        return exerciseLogRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(exerciseLogMapper::toResponse)
                .toList();
    }

    @Override
    public ExerciseLogResponse create(ExerciseLogRequest dto) {
        ExerciseLog exerciseLog = exerciseLogMapper.toEntity(dto);

        exerciseLog.setUser(fetchUser(dto.userId()));
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));

        if (dto.workoutId() != null) exerciseLog.setWorkout(fetchWorkout(dto.workoutId()));

        if (exerciseLog.getSets() != null) exerciseLog.getSets().forEach(set -> set.setExerciseLog(exerciseLog));

        return exerciseLogMapper.toResponse(exerciseLogRepository.save(exerciseLog));
    }

    @Override
    public ExerciseLogResponse update(Long id, ExerciseLogRequest dto) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + id));

        exerciseLogMapper.updateFromDto(dto, exerciseLog);

        exerciseLog.setUser(fetchUser(dto.userId()));
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));
        if (dto.workoutId() != null) exerciseLog.setWorkout(fetchWorkout(dto.workoutId()));
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


    private User fetchUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
    }

    private Exercise fetchExercise(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found: " + exerciseId));
    }

    private Workout fetchWorkout(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + workoutId));
    }
}
