package com.parkingon.repository;

import com.parkingon.entity.LprDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LprDeviceRepository extends JpaRepository<LprDevice, Long> {
    List<LprDevice> findByApartmentId(Long apartmentId);
}
