package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "apartments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 200)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(name = "total_dong")
    private Integer totalDong;

    @Column(name = "total_ho")
    private Integer totalHo;

    @Column(length = 20)
    private String status; // ACTIVE, INACTIVE, ERROR

    @Column(name = "contract_start_date")
    private LocalDateTime contractStartDate;

    @Column(name = "contract_end_date")
    private LocalDateTime contractEndDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = "ACTIVE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
