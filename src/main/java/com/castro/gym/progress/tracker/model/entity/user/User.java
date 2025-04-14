package com.castro.gym.progress.tracker.model.entity.user;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private Double height;
    private Double currentWeight;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    private List<BodyMeasurement> measurements;
    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;
    @OneToMany(mappedBy = "user")
    private List<ExerciseLog> logs;
}
