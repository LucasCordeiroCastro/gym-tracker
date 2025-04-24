package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.config.NotFoundException;
import com.castro.gym.progress.tracker.model.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.model.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import com.castro.gym.progress.tracker.model.entity.workout.WorkoutExercise;
import com.castro.gym.progress.tracker.model.mapper.WorkoutMapper;
import com.castro.gym.progress.tracker.repository.ExerciseRepository;
import com.castro.gym.progress.tracker.repository.UserRepository;
import com.castro.gym.progress.tracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WorkoutService extends AbstractCrudService<
        Workout,
        Long,
        WorkoutRequest,
        WorkoutResponse
        > {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository,
                          ExerciseRepository exerciseRepository, WorkoutMapper workoutMapper) {
        super(workoutRepository, workoutMapper::toEntity, workoutMapper::toResponse, workoutMapper::updateFromDto);
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutMapper = workoutMapper;
    }

    public List<WorkoutResponse> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(workoutMapper::toResponse)
                .toList();
    }

    @Override
    public WorkoutResponse create(WorkoutRequest dto) {
        Workout workout = workoutMapper.toEntity(dto);

        User user = fetchUser(dto.userId());
        workout.setUser(user);

        List<WorkoutExercise> workoutExercises = buildWorkoutExercises(dto.exerciseIds(), workout);
        workout.setExercises(workoutExercises);

        return workoutMapper.toResponse(workoutRepository.save(workout));
    }

    @Override
    public WorkoutResponse update(Long id, WorkoutRequest dto) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        workoutMapper.updateFromDto(dto, workout);

        User user = fetchUser(dto.userId());
        workout.setUser(user);

        List<WorkoutExercise> workoutExercises = buildWorkoutExercises(dto.exerciseIds(), workout);
        workout.setExercises(workoutExercises);

        return workoutMapper.toResponse(workoutRepository.save(workout));
    }

    private User fetchUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
    }

    private List<WorkoutExercise> buildWorkoutExercises(List<Long> exerciseIds, Workout workout) {
        if (exerciseIds == null || exerciseIds.isEmpty()) return Collections.emptyList();

        Map<Long, Exercise> exerciseMap = exerciseRepository.findAllById(exerciseIds).stream()
                .collect(Collectors.toMap(Exercise::getId, Function.identity()));

        if (exerciseMap.size() != exerciseIds.size()) {
            throw new NotFoundException("Some exercises were not found.");
        }

        List<WorkoutExercise> workoutExercises = new ArrayList<>();
        for (int i = 0; i < exerciseIds.size(); i++) {
            Long id = exerciseIds.get(i);
            Exercise exercise = exerciseMap.get(id);

            workoutExercises.add(
                    WorkoutExercise.builder()
                            .workout(workout)
                            .exercise(exercise)
                            .exerciseOrder(i + 1)
                            .build()
            );
        }
        return workoutExercises;
    }

}
