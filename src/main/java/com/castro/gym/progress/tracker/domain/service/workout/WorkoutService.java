package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.domain.entity.user.User;
import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import com.castro.gym.progress.tracker.domain.entity.workout.WorkoutExercise;
import com.castro.gym.progress.tracker.domain.repository.user.UserRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseRepository;
import com.castro.gym.progress.tracker.domain.repository.workout.WorkoutRepository;
import com.castro.gym.progress.tracker.domain.service.user.UserAuthorizationHelper;
import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.mapper.WorkoutMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserAuthorizationHelper userAuthorizationHelper;

    public List<WorkoutResponse> getWorkoutsByUser() {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(workoutMapper::toResponse)
                .toList();
    }

    public WorkoutResponse findById(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        Workout workout = workoutRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        return workoutMapper.toResponse(workout);
    }

    public WorkoutResponse create(WorkoutRequest dto) {
        User user = userAuthorizationHelper.getAuthenticatedUser();
        Workout workout = workoutMapper.toEntity(dto);
        workout.setUser(user);

        List<WorkoutExercise> workoutExercises = buildWorkoutExercises(dto.exerciseIds(), workout);
        workout.setExercises(workoutExercises);

        return workoutMapper.toResponse(workoutRepository.save(workout));
    }

    public WorkoutResponse update(Long id, WorkoutRequest dto) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        Workout workout = workoutRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        workoutMapper.updateFromDto(dto, workout);

        List<WorkoutExercise> workoutExercises = buildWorkoutExercises(dto.exerciseIds(), workout);
        workout.setExercises(workoutExercises);

        return workoutMapper.toResponse(workoutRepository.save(workout));
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

    public void delete(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        Workout workout = workoutRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        workoutRepository.delete(workout);
    }
}
