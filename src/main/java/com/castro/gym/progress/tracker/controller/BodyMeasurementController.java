package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.service.BodyMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/body-measurements")
public class BodyMeasurementController {
    private final BodyMeasurementService bodyMeasurementService;

    @GetMapping("/user/{userId}")
    public List<BodyMeasurement> getByUser(@PathVariable Long userId) {
        return bodyMeasurementService.findByUser(userId);
    }

    @PostMapping
    public BodyMeasurement create(@RequestBody BodyMeasurement bodyMeasurement) {
        return bodyMeasurementService.save(bodyMeasurement);
    }
}
