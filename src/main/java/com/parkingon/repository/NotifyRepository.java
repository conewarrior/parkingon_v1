package com.parkingon.repository;

import com.parkingon.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Long> {
    List<Notify> findByIsActiveTrueOrderBySendDateDesc();
}
