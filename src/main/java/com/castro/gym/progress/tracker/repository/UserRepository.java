package com.castro.gym.progress.tracker.repository;

import com.castro.gym.progress.tracker.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
