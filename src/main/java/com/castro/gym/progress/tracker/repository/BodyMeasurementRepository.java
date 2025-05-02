package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {
    List<BodyMeasurement> findByUserIdOrderByDateDesc(Long userId);
    Optional<BodyMeasurement> findByIdAndUserId(Long id, Long userId);
}
