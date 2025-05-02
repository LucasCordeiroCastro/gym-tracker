package com.castro.gym.progress.tracker.domain.service.user;

import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.domain.entity.user.User;
import com.castro.gym.progress.tracker.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthorizationHelper {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }

    public void checkOwnership(Long entityUserId, String resource) {
        if (!entityUserId.equals(getAuthenticatedUserId())) {
            throw new AccessDeniedException("You do not have access to " + resource + " with User ID " + entityUserId);
        }
    }
}
