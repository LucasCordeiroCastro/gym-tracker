package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.response.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.service.BodyMeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/body-measurements")
@RestController
public class BodyMeasurementController {
    private final BodyMeasurementService bodyMeasurementService;

    @GetMapping("/user/{userId}")
    public List<BodyMeasurementResponse> getByUser(@PathVariable Long userId) {
        return bodyMeasurementService.findByUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMeasurementResponse registerBodyMeasurement(@RequestBody @Valid BodyMeasurementRequest bodyMeasurementRequest) {
        return bodyMeasurementService.register(bodyMeasurementRequest);
    }
}
