package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vocs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "car_number", length = 20)
    private String carNumber;

    @Column(name = "customer_type", length = 20)
    private String customerType; // 일반, 입주민, 방문, 출역 등

    @Column(name = "visit_purpose", length = 50)
    private String visitPurpose; // 방문, 배달, 택배 등

    @Column(length = 10)
    private String dong;

    @Column(length = 10)
    private String ho;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", length = 20)
    private String status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = "OPEN";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
