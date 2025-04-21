package com.castro.gym.progress.tracker.model.entity.log;

import com.castro.gym.progress.tracker.model.entity.workout.Exercise;
import com.castro.gym.progress.tracker.model.entity.workout.Workout;
import com.castro.gym.progress.tracker.model.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise_logs")
public class ExerciseLog {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private Workout workout;

    @Builder.Default
    @OneToMany(mappedBy = "exerciseLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetEntry> sets = new ArrayList<>();
}
