package com.parkingon.repository;

import com.parkingon.entity.InOutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InOutHistoryRepository extends JpaRepository<InOutHistory, Long> {
    List<InOutHistory> findByApartmentIdOrderByInoutTimeDesc(Long apartmentId);
}
