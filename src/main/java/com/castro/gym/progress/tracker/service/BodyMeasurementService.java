package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.BodyMeasurementMapper;
import com.castro.gym.progress.tracker.repository.BodyMeasurementRepository;
import com.castro.gym.progress.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BodyMeasurementService {
    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final UserRepository userRepository;
    private final BodyMeasurementMapper bodyMeasurementMapper;

    public List<BodyMeasurementResponse> findByUser(Long userId) {
        return bodyMeasurementRepository.findByUserIdOrderByDateAsc(userId).stream()
                .map(bodyMeasurementMapper::toResponse).toList();
    }

    public BodyMeasurementResponse register(BodyMeasurementRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BodyMeasurement bodyMeasurement = bodyMeasurementMapper.toEntity(dto);
        bodyMeasurement.setUser(user);

        BodyMeasurement saved = bodyMeasurementRepository.save(bodyMeasurement);
        return bodyMeasurementMapper.toResponse(saved);
    }
}
