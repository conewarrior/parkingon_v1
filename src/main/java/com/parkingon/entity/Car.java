package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "car_number", unique = true, nullable = false, length = 20)
    private String carNumber;

    @Column(length = 10)
    private String dong;

    @Column(length = 10)
    private String ho;

    @Column(name = "owner_name", length = 50)
    private String ownerName;

    @Column(name = "owner_phone", length = 20)
    private String ownerPhone;

    @Column(name = "car_type", length = 20)
    private String carType; // 입주민, 방문, 출역, 화이트 등

    @Column(name = "is_registered")
    private Boolean isRegistered;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (isRegistered == null) {
            isRegistered = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
