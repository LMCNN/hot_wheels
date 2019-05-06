package com.dbg.hot_wheels.repository;

import com.dbg.hot_wheels.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
