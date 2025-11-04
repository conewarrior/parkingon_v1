package com.parkingon.repository;

import com.parkingon.entity.Voc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocRepository extends JpaRepository<Voc, Long> {
}
