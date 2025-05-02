package com.castro.gym.progress.tracker.domain.entity.user;

import com.castro.gym.progress.tracker.domain.entity.workout.ExerciseLog;
import com.castro.gym.progress.tracker.domain.entity.workout.Workout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<BodyMeasurement> measurements;

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @OneToMany(mappedBy = "user")
    private List<ExerciseLog> logs;
}
