package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lpr_devices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LprDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "device_code", length = 50)
    private String deviceCode;

    @Column(name = "device_type", length = 20)
    private String deviceType; // 입구LPR, 출구LPR, CCTV

    @Column(name = "device_name", length = 100)
    private String deviceName;

    @Column(name = "device_url", length = 200)
    private String deviceUrl;

    @Column(name = "voip", length = 20)
    private String voip;

    @Column(name = "rtsp_url", length = 500)
    private String rtspUrl;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (isActive == null) {
            isActive = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
