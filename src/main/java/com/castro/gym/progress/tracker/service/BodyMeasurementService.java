package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.dto.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.model.entity.user.User;
import com.castro.gym.progress.tracker.model.mapper.BodyMeasurementMapper;
import com.castro.gym.progress.tracker.repository.BodyMeasurementRepository;
import com.castro.gym.progress.tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyMeasurementService extends AbstractCrudService<
        BodyMeasurement,
        Long,
        BodyMeasurementRequest,
        BodyMeasurementResponse
        > {

    private final UserRepository userRepository;
    private final BodyMeasurementMapper bodyMeasurementMapper;

    public BodyMeasurementService(BodyMeasurementRepository repo,
                                  BodyMeasurementMapper bodyMeasurementMapper,
                                  UserRepository userRepository) {
        super(repo, bodyMeasurementMapper::toEntity, bodyMeasurementMapper::toResponse, bodyMeasurementMapper::updateFromDto);
        this.userRepository = userRepository;
        this.bodyMeasurementMapper = bodyMeasurementMapper;
    }

    public List<BodyMeasurementResponse> findByUser(Long userId) {
        return ((BodyMeasurementRepository) repo).findByUserIdOrderByDateAsc(userId).stream()
                .map(bodyMeasurementMapper::toResponse).toList();
    }

    public BodyMeasurementResponse register(BodyMeasurementRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BodyMeasurement bodyMeasurement = bodyMeasurementMapper.toEntity(dto);
        bodyMeasurement.setUser(user);

        BodyMeasurement saved = repo.save(bodyMeasurement);
        return bodyMeasurementMapper.toResponse(saved);
    }
}
