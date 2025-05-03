package com.castro.gym.progress.tracker.api.user.controller;

import com.castro.gym.progress.tracker.api.user.dto.request.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.api.user.dto.response.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.domain.service.user.BodyMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "BodyMeasurement", description = "Manage body measurement templates")
@RequestMapping("/api/v1/body-measurements")
@RestController
public class BodyMeasurementController {
    private final BodyMeasurementService bodyMeasurementService;

    @Operation(summary = "Create a new body measurement")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMeasurementResponse createBodyMeasurement(@Valid @RequestBody BodyMeasurementRequest bodyMeasurementRequest) {
        return bodyMeasurementService.create(bodyMeasurementRequest);
    }

    @Operation(summary = "Get all body measurements of user")
    @GetMapping("/user")
    public List<BodyMeasurementResponse> getAllByUser() {
        return bodyMeasurementService.findByUser();
    }

    @Operation(summary = "Get body measurement by ID")
    @GetMapping("/{id}")
    public BodyMeasurementResponse getBodyMeasurementById(@PathVariable Long id) {
        return bodyMeasurementService.findById(id);
    }

    @Operation(summary = "Update body measurement by ID")
    @PutMapping("/{id}")
    public BodyMeasurementResponse updateBodyMeasurement(@PathVariable Long id, @Valid @RequestBody BodyMeasurementRequest updatedBodyMeasurement) {
        return bodyMeasurementService.update(id, updatedBodyMeasurement);
    }

    @Operation(summary = "Delete body measurement by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBodyMeasurement(@PathVariable Long id) {
        bodyMeasurementService.delete(id);
    }
}
