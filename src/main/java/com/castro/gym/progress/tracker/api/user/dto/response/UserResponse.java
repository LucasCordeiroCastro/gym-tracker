package com.castro.gym.progress.tracker.api.user.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email,
        String height,
        String birthDate
) {
}
