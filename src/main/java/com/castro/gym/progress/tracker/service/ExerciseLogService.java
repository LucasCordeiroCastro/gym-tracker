package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.config.NotFoundException;
import com.castro.gym.progress.tracker.model.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import com.castro.gym.progress.tracker.model.mapper.ExerciseLogMapper;
import com.castro.gym.progress.tracker.repository.ExerciseLogRepository;
import com.castro.gym.progress.tracker.repository.ExerciseRepository;
import com.castro.gym.progress.tracker.repository.UserRepository;
import com.castro.gym.progress.tracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseLogService extends AbstractCrudService<
        ExerciseLog,
        Long,
        ExerciseLogRequest,
        ExerciseLogResponse
        > {

    private final ExerciseLogRepository exerciseLogRepository;
    private final ExerciseLogMapper exerciseLogMapper;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public ExerciseLogService(ExerciseLogRepository exerciseLogRepository, ExerciseLogMapper exerciseLogMapper,
                              UserRepository userRepository, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        super(exerciseLogRepository, exerciseLogMapper::toEntity, exerciseLogMapper::toResponse, exerciseLogMapper::updateFromDto);
        this.exerciseLogRepository = exerciseLogRepository;
        this.exerciseLogMapper = exerciseLogMapper;
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

    @Override
    public ExerciseLogResponse create(ExerciseLogRequest dto) {
        ExerciseLog exerciseLog = exerciseLogMapper.toEntity(dto);

        exerciseLog.setUser(fetchUser(dto.userId()));
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));

        if (dto.workoutId() != null) {
            exerciseLog.setWorkout(fetchWorkout(dto.workoutId()));
        }
        return exerciseLogMapper.toResponse(exerciseLogRepository.save(exerciseLog));
    }

    @Override
    public ExerciseLogResponse update(Long id, ExerciseLogRequest dto) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ExerciseLog not found: " + id));

        exerciseLogMapper.updateFromDto(dto, exerciseLog);

        exerciseLog.setUser(fetchUser(dto.userId()));
        exerciseLog.setExercise(fetchExercise(dto.exerciseId()));

        if (dto.workoutId() != null) {
            exerciseLog.setWorkout(fetchWorkout(dto.workoutId()));
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
