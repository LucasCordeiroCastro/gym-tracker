package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.repository.BodyMeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BodyMeasurementService {
    private final BodyMeasurementRepository bodyMeasurementRepository;

    public List<BodyMeasurement> findByUser(Long userId) {
        return bodyMeasurementRepository.findByUserIdOrderByDateAsc(userId);
    }

    public BodyMeasurement save(BodyMeasurement bodyMeasurement) {
        return bodyMeasurementRepository.save(bodyMeasurement);
    }
}
