package com.castro.gym.progress.tracker.model.entity.workout;

import com.castro.gym.progress.tracker.model.entity.log.ExerciseLog;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection(targetClass = MuscleGroupEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_muscle_groups", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "muscle_group")
    private List<MuscleGroupEnum> muscleGroups;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutLinks;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseLog> logs;
}
