package com.castro.gym.progress.tracker.api;

import com.castro.gym.progress.tracker.domain.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Deal with user authentication")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Authenticate with email+password (returns JWT)",
            security = @SecurityRequirement(name = "basicAuth")
    )
    @PostMapping("/authenticate")
    public Map<String, String> authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }
}
