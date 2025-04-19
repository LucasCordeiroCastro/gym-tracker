package com.castro.gym.progress.tracker.model.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "body_measurements")
public class BodyMeasurement {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private Double weight;
    private Double waist;
    private Double neck;
    private Double hip;
    private Double bodyFatPercentage;
    private Double chest;
    private Double bicep;
    private String notes;

    @ManyToOne
    private User user;
}
