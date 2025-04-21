package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.UserRequest;
import com.castro.gym.progress.tracker.model.dto.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface UserMapper {
    User toEntity(UserRequest dto);
    UserResponse toResponse(User entity);
}
