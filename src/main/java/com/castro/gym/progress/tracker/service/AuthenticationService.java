package com.castro.gym.progress.tracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;

    public Map<String, String> authenticate(Authentication authentication) {
        String jwt = jwtService.generateToken(authentication);
        return Map.of("access_token", jwt);
    }
}
