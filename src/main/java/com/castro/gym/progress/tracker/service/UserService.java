package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.config.EmailAlreadyExistsException;
import com.castro.gym.progress.tracker.config.InvalidOldPasswordException;
import com.castro.gym.progress.tracker.model.dto.request.CreateUserRequest;
import com.castro.gym.progress.tracker.model.dto.request.UpdatePasswordRequest;
import com.castro.gym.progress.tracker.model.dto.request.UpdateUserRequest;
import com.castro.gym.progress.tracker.model.dto.response.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.UserMapper;
import com.castro.gym.progress.tracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorizationHelper userAuthorizationHelper;

    public UserResponse createUser(CreateUserRequest dto) {
        if (userRepository.existsByEmail(dto.email()))
            throw new EmailAlreadyExistsException("Email is already in use");

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse getUser() {
        return userMapper.toResponse(userAuthorizationHelper.getAuthenticatedUser());
    }

    public UserResponse updateUser(UpdateUserRequest updatedUser) {
        User user = userAuthorizationHelper.getAuthenticatedUser();
        userMapper.updateFromDto(updatedUser, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updatePassword(UpdatePasswordRequest updatedPassword) {
        User user = userAuthorizationHelper.getAuthenticatedUser();

        if (!passwordEncoder.matches(updatedPassword.oldPassword(), user.getPassword())) {
            throw new InvalidOldPasswordException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(updatedPassword.newPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }
}
