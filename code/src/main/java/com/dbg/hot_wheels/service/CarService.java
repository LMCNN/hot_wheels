package com.dbg.hot_wheels.service;

import com.dbg.hot_wheels.entity.Car;
import com.dbg.hot_wheels.entity.MaintenanceRecord;
import com.dbg.hot_wheels.entity.Mechanic;
import com.dbg.hot_wheels.entity.RepairType;
import com.dbg.hot_wheels.repository.CarRepository;
import com.dbg.hot_wheels.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    MaintenanceRepository maintenanceRepository;

    @Autowired
    CarRepository carRepository;

    public Collection<MaintenanceRecord> getRecords(){
        return maintenanceRepository.findAll();
    }

    public Collection<MaintenanceRecord> getCarRecords(Long id){
        Car car = carRepository.getOne(id);
        return car.getMaintenanceRecords();
    }

    public Collection<Mechanic> getMM(Long id){
        Collection<Mechanic> result = new LinkedList<>();
        result.add(maintenanceRepository.getOne(id).getMechanic());
        return result;
    }

    public Collection<RepairType> getMT(Long id){
        Collection<RepairType> result = new LinkedList<>();
        result.add(maintenanceRepository.getOne(id).getRepairType());
        return result;
    }
}
