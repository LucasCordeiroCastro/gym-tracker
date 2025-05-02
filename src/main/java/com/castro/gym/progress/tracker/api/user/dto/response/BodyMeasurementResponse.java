package com.castro.gym.progress.tracker.api.user.dto.response;

import java.time.LocalDate;

public record BodyMeasurementResponse(
        Long id,
        LocalDate date,
        Long userId,
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
