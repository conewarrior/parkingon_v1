package com.parkingon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 100)
    private String sender;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "notify_type", length = 20)
    private String notifyType; // POPUP, ALERT

    @Column(name = "target_type", length = 20)
    private String targetType; // ALL, APARTMENT, USER

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (sendDate == null) {
            sendDate = LocalDateTime.now();
        }
        if (isActive == null) {
            isActive = true;
        }
    }
}
