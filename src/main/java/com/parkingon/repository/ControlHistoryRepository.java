package com.parkingon.repository;

import com.parkingon.entity.ControlHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlHistoryRepository extends JpaRepository<ControlHistory, Long> {
    List<ControlHistory> findByApartmentIdOrderByControlTimeDesc(Long apartmentId);
}
