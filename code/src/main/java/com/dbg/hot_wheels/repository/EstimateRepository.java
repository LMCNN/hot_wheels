package com.dbg.hot_wheels.repository;

import com.dbg.hot_wheels.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate, String> {
    //Type: upgrade computer
    //      upgrade transmission
    //      upgrade engine
    @Query(nativeQuery = true)
    List<Estimate> estimateRepair(@Param("type") String type);
}
