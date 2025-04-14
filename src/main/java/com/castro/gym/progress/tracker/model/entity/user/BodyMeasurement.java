package com.castro.gym.progress.tracker.model.entity.user;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private String notes;

    @ManyToOne
    private User user;
}
