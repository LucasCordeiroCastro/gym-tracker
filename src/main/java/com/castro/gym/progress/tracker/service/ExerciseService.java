package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.model.mapper.ExerciseMapper;
import com.castro.gym.progress.tracker.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService extends AbstractCrudService<
        Exercise,
        Long,
        ExerciseRequest,
        ExerciseResponse
        > {

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        super(exerciseRepository, exerciseMapper::toEntity, exerciseMapper::toResponse, exerciseMapper::updateFromDto);
    }
}
