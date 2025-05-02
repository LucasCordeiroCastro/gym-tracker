package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.service.AbstractCrudService;
import com.castro.gym.progress.tracker.mapper.ExerciseMapper;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseRepository;
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
