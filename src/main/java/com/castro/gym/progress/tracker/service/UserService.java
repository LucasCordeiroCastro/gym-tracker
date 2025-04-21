package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.UserRequest;
import com.castro.gym.progress.tracker.model.dto.UserResponse;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.UserMapper;
import com.castro.gym.progress.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.map(userMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse createUser(UserRequest dto) {
        User newUser = userMapper.toEntity(dto);
        userRepository.save(newUser);
        return userMapper.toResponse(newUser);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setHeight(updatedUser.getHeight());
            user.setBirthDate(updatedUser.getBirthDate());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
