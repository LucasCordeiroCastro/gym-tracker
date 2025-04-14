package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import com.castro.gym.progress.tracker.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Optional<Workout> updateWorkout(Long id, Workout updatedWorkout) {
        return workoutRepository.findById(id).map(workout -> {
            workout.setName(updatedWorkout.getName());
            workout.setDescription(updatedWorkout.getDescription());
            return workoutRepository.save(workout);
        });
    }

    public boolean deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) return false;
        workoutRepository.deleteById(id);
        return true;
    }
}
