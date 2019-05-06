package com.dbg.hot_wheels.controller;

import com.dbg.hot_wheels.entity.MaintenanceRecord;
import com.dbg.hot_wheels.entity.Mechanic;
import com.dbg.hot_wheels.entity.RepairType;
import com.dbg.hot_wheels.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RecordController {

    @Autowired
    CarService carService;

    @GetMapping("/getRecords")
    public Collection<MaintenanceRecord> getRecords(){
        return carService.getRecords();
    }

    @GetMapping("/getCarRecords")
    public Collection<MaintenanceRecord> getCarRecords(@RequestParam("id") Long id){
        return carService.getCarRecords(id);
    }

    @GetMapping("/getmm")
    public Collection<Mechanic> getmm(@RequestParam("id") Long id){
        return carService.getMM(id);
    }

    @GetMapping("/getmt")
    public Collection<RepairType> getmt(@RequestParam("id") Long id){
        return carService.getMT(id);
    }
}
