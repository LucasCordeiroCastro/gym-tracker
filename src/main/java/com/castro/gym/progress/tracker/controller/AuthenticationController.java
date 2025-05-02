package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public Map<String, String> authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }
}
