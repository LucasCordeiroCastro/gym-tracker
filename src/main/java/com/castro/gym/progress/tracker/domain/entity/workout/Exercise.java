package com.castro.gym.progress.tracker.domain.entity.workout;

import com.castro.gym.progress.tracker.domain.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection(targetClass = MuscleGroup.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_muscle_groups", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "muscle_group")
    private List<MuscleGroup> muscleGroups;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutExercises;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseLog> exerciseLogs;
}
