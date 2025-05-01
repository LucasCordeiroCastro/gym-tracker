package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }
}
