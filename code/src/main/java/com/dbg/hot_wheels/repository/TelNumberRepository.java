package com.dbg.hot_wheels.repository;

import com.dbg.hot_wheels.entity.TelNumber;
import com.dbg.hot_wheels.entity.TelNumberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Repository
//@Transactional(readOnly = false)
public interface TelNumberRepository extends JpaRepository<TelNumber, TelNumberId> {
}
