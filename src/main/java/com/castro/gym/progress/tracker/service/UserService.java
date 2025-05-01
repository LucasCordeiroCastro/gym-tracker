package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.request.UserRequest;
import com.castro.gym.progress.tracker.model.dto.response.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.UserMapper;
import com.castro.gym.progress.tracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<
        User,
        Long,
        UserRequest,
        UserResponse
        > {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        super(userRepository, userMapper::toEntity, userMapper::toResponse, userMapper::updateFromDto);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserRequest dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));

        return userMapper.toResponse(userRepository.save(user));
    }
}
