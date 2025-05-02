package com.castro.gym.progress.tracker.mapper;

import com.castro.gym.progress.tracker.api.workout.dto.request.ExerciseLogRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.ExerciseLogResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {SetEntryMapper.class},
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ExerciseLogMapper {
    ExerciseLog toEntity(ExerciseLogRequest dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "exerciseId", source = "exercise.id")
    @Mapping(target = "exerciseName", source = "exercise.name")
    @Mapping(target = "workoutId", source = "workout", qualifiedByName = "workoutIdHandling")
    @Mapping(target = "workoutName", source = "workout", qualifiedByName = "workoutNameHandling")
    @Mapping(
            target = "totalVolume",
            expression = "java(calculateTotalVolume(entity))"
    )
    ExerciseLogResponse toResponse(ExerciseLog entity);

    void updateFromDto(ExerciseLogRequest dto, @MappingTarget ExerciseLog entity);

    default Double calculateTotalVolume(ExerciseLog log) {
        if (log.getSets() == null || log.getSets().isEmpty()) return 0.0;

        return log.getSets().stream()
                .mapToDouble(set -> set.getReps() * set.getWeight())
                .sum();
    }

    @Named("workoutIdHandling")
    default Long workoutIdHandling(Workout workout) {
        return workout != null ? workout.getId() : null;
    }

    @Named("workoutNameHandling")
    default String workoutNameHandling(Workout workout) {
        return workout != null ? workout.getName() : null;
    }
}
