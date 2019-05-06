package com.dbg.hot_wheels.controller;

import com.dbg.hot_wheels.entity.Certification;
import com.dbg.hot_wheels.entity.Mechanic;
import com.dbg.hot_wheels.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MechanicController {

    @Autowired
    MechanicService mechanicService;

    @GetMapping("/me")
    public Collection<Mechanic> getMechanics()
    {
        return mechanicService.getMechanics();
    }

    @GetMapping("/mce")
    public Collection<Certification> getMeCertifications(@RequestParam("id") Long id)
    {
        return mechanicService.getMeCertification(id);
    }
}
