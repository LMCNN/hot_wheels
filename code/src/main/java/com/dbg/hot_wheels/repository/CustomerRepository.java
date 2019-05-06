package com.dbg.hot_wheels.repository;

import com.dbg.hot_wheels.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Transactional(readOnly = false)
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(@Param("name") String name);
}
