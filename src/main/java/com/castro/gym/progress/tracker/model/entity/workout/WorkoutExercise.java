package com.castro.gym.progress.tracker.model.entity.workout;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {
    @Id @GeneratedValue
    private Long id;
    private Integer exerciseOrder;

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Exercise exercise;
}
