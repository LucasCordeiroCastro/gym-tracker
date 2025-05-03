package com.castro.gym.progress.tracker.domain.entity.workout;

import com.castro.gym.progress.tracker.domain.enums.SetDifficulty;
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
@Table(name = "set_entries")
public class SetEntry {
    @Id
    @GeneratedValue
    private Long id;

    private Integer reps;
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private SetDifficulty difficulty = SetDifficulty.UNSPECIFIED;

    @ManyToOne
    private ExerciseLog exerciseLog;
}
