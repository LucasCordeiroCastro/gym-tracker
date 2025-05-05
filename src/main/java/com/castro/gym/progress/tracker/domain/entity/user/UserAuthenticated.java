package com.castro.gym.progress.tracker.domain.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthenticated implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserAuthenticated(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

