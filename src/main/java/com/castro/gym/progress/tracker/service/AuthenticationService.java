package com.castro.gym.progress.tracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
