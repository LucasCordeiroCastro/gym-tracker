package com.castro.gym.progress.tracker.domain.entity.workout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
