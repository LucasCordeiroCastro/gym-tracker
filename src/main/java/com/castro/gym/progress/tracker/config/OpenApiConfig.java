package com.castro.gym.progress.tracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "basicAuth",
        description = "Enter your email and password to access this API",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Enter your JWT token to access this API",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Gym Progress Tracker API",
                version = "1.0.0",
                description = "REST API that allows users to manage their workouts, log exercises, " +
                        "track body measurements, and monitor progress over time. "
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@Configuration
public class OpenApiConfig {
}

