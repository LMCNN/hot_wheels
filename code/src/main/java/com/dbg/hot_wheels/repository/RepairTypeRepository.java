package com.dbg.hot_wheels.repository;

import com.dbg.hot_wheels.entity.RepairType;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, Long> {

    @Query(nativeQuery = true)
    public JSONObject toList();
}
