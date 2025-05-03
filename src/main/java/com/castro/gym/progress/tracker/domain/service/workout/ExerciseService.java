package com.castro.gym.progress.tracker.domain.service.workout;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.Exercise;
import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import com.castro.gym.progress.tracker.domain.repository.workout.ExerciseRepository;
import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.mapper.ExerciseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseResponse findById(Long id) {
        return exerciseRepository.findById(id)
                .map(exerciseMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Exercise not found"));
    }

    public List<ExerciseResponse> findAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(exerciseMapper::toResponse)
                .toList();
    }
    public List<ExerciseResponse> findByMuscleGroup(MuscleGroup muscleGroup) {
        return exerciseRepository.findByMuscleGroupsContaining(muscleGroup)
                .stream()
                .map(exerciseMapper::toResponse)
                .toList();
    }

    public ExerciseResponse create(ExerciseRequest dto) {
        Exercise exercise = exerciseMapper.toEntity(dto);
        return exerciseMapper.toResponse(exerciseRepository.save(exercise));
    }

    public ExerciseResponse update(Long id, ExerciseRequest dto) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exercise not found"));
        exerciseMapper.updateFromDto(dto, exercise);
        return exerciseMapper.toResponse(exerciseRepository.save(exercise));
    }

    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }
}
