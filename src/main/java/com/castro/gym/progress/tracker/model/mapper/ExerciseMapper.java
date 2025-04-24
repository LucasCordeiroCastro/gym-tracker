package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.request.ExerciseRequest;
import com.castro.gym.progress.tracker.model.dto.response.ExerciseResponse;
import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ExerciseMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workoutExercises", ignore = true)
    @Mapping(target = "exerciseLogs", ignore = true)
    Exercise toEntity(ExerciseRequest dto);

    ExerciseResponse toResponse(Exercise entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workoutExercises", ignore = true)
    @Mapping(target = "exerciseLogs", ignore = true)
    void updateFromDto(ExerciseRequest dto, @MappingTarget Exercise entity);
}
