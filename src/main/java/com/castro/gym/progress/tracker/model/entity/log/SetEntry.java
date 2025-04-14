package com.castro.gym.progress.tracker.model.entity.log;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "set_entries")
public class SetEntry {
    @Id @GeneratedValue
    private Long id;

    private Integer setOrder;
    private Integer reps;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private SetDifficultyEnum setDifficultyEnum;

    @ManyToOne
    private ExerciseLog exerciseLog;
}
