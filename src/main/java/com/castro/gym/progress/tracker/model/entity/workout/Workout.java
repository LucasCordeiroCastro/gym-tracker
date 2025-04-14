package com.castro.gym.progress.tracker.model.entity.workout;

import com.castro.gym.progress.tracker.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "workouts")
public class Workout {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "workout")
    private List<WorkoutExercise> exercises;
}
