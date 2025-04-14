package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> findByMuscleGroup(String group) {
        return exerciseRepository.findByMuscleGroup(group);
    }

    public Exercise save(Exercise e) {
        return exerciseRepository.save(e);
    }
}
