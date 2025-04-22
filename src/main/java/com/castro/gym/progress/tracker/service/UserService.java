package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.UserRequest;
import com.castro.gym.progress.tracker.model.dto.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.UserMapper;
import com.castro.gym.progress.tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<
        User,
        Long,
        UserRequest,
        UserResponse
        > {

    public UserService(UserRepository repo, UserMapper userMapper) {
        super(repo, userMapper::toEntity, userMapper::toResponse, userMapper::updateFromDto);
    }

}
