package com.castro.gym.progress.tracker.mapper;

import com.castro.gym.progress.tracker.api.user.dto.request.CreateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.request.UpdateUserRequest;
import com.castro.gym.progress.tracker.api.user.dto.response.UserResponse;
import com.castro.gym.progress.tracker.domain.entity.user.User;
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
    User toEntity(CreateUserRequest dto);

    UserResponse toResponse(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "workouts", ignore = true)
    @Mapping(target = "logs", ignore = true)
    void updateFromDto(UpdateUserRequest dto, @MappingTarget User entity);
}
