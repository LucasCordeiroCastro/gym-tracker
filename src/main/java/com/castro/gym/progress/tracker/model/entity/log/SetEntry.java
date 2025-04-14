package com.castro.gym.progress.tracker.model.entity.log;

import jakarta.persistence.*;

@Entity
@Table(name = "set_entries")
public class SetEntry {
    @Id @GeneratedValue
    private Long id;

    private Integer setOrder;
    private Integer reps;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne
    private ExerciseLog exerciseLog;
}
