package com.castro.gym.progress.tracker.api.user.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BodyMeasurementRequest(
        @NotNull(message = "Date is required") LocalDate date,
        Double weight,
        Double waist,
        Double neck,
        Double hip,
        Double bodyFatPercentage,
        Double chest,
        Double bicep,
        String notes
) {
}