package com.parkingon.repository;

import com.parkingon.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findByGroupCodeOrderBySortOrder(String groupCode);
}
