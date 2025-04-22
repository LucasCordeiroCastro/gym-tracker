package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.request.UserRequest;
import com.castro.gym.progress.tracker.model.dto.response.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "workouts", ignore = true)
    @Mapping(target = "logs", ignore = true)
    User toEntity(UserRequest dto);

    UserResponse toResponse(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "workouts", ignore = true)
    @Mapping(target = "logs", ignore = true)
    void updateFromDto(UserRequest dto, @MappingTarget User entity);
}
