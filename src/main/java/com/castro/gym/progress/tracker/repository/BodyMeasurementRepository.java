package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {
    List<BodyMeasurement> findByUserIdOrderByDateAsc(Long userId);
}
