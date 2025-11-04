package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inout_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InOutHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "car_number", length = 20)
    private String carNumber;

    @Column(name = "inout_type", length = 10)
    private String inoutType; // IN, OUT

    @Column(length = 10)
    private String dong;

    @Column(length = 10)
    private String ho;

    @Column(name = "gate_name", length = 50)
    private String gateName;

    @Column(name = "customer_type", length = 20)
    private String customerType;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "inout_time")
    private LocalDateTime inoutTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (inoutTime == null) {
            inoutTime = LocalDateTime.now();
        }
    }
}
