package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.service.BodyMeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/body-measurements")
public class BodyMeasurementController {
    private final BodyMeasurementService bodyMeasurementService;

    @GetMapping("/user/{userId}")
    public List<BodyMeasurementResponse> getByUser(@PathVariable Long userId) {
        return bodyMeasurementService.findByUser(userId);
    }

    @PostMapping
    public ResponseEntity<BodyMeasurementResponse> registerBodyMeasurement(@RequestBody @Valid BodyMeasurementRequest bodyMeasurementRequest) {
        BodyMeasurementResponse response = bodyMeasurementService.register(bodyMeasurementRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
