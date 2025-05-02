package com.castro.gym.progress.tracker.mapper;

import com.castro.gym.progress.tracker.api.workout.dto.request.WorkoutRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.WorkoutResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
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
