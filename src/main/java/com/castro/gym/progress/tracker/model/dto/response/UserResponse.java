package com.castro.gym.progress.tracker.model.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email,
        String height,
        String birthDate
) {
}
