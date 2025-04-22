package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.model.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkoutMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    Workout toEntity(WorkoutRequest dto);

    WorkoutResponse toResponse(Workout entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    void updateFromDto(WorkoutRequest dto,  @MappingTarget Workout entity);
}
