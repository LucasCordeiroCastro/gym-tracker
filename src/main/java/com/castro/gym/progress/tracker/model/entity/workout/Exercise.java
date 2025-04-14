package com.castro.gym.progress.tracker.model.entity.workout;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String muscleGroup;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutLinks;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseLog> logs;
}
