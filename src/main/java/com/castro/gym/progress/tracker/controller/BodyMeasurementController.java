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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMeasurementResponse createBodyMeasurement(@Valid @RequestBody BodyMeasurementRequest bodyMeasurementRequest) {
        return bodyMeasurementService.create(bodyMeasurementRequest);
    }

    @GetMapping("/user")
    public List<BodyMeasurementResponse> getAllByUser() {
        return bodyMeasurementService.findByUser();
    }

    @GetMapping("/{id}")
    public BodyMeasurementResponse getBodyMeasurementById(@PathVariable Long id) {
        return bodyMeasurementService.findById(id);
    }

    @PutMapping("/{id}")
    public BodyMeasurementResponse updateBodyMeasurement(@PathVariable Long id, @Valid @RequestBody BodyMeasurementRequest updatedBodyMeasurement) {
        return bodyMeasurementService.update(id, updatedBodyMeasurement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBodyMeasurement(@PathVariable Long id) {
        bodyMeasurementService.delete(id);
    }
}
