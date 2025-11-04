package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "control_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "car_number", length = 20)
    private String carNumber;

    @Column(name = "control_type", length = 20)
    private String controlType; // ALLOW, DENY

    @Column(name = "gate_name", length = 50)
    private String gateName;

    @Column(name = "controller", length = 50)
    private String controller;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(name = "control_time")
    private LocalDateTime controlTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (controlTime == null) {
            controlTime = LocalDateTime.now();
        }
    }
}
