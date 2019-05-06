package com.dbg.hot_wheels.controller;

import com.dbg.hot_wheels.entity.*;
import com.dbg.hot_wheels.repository.*;
import com.dbg.hot_wheels.service.RepairService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class RepairController {

    @Autowired
    RepairService repairService;

    @GetMapping("/getTypes")
    public Collection<RepairType> getTypes(){
        System.out.println("getting repair types");
        return repairService.getRepairs();
    }

    @GetMapping("/getParts")
    public Collection<Part> getParts(@RequestParam("id") Long id){
        return repairService.getParts(id);
    }

    @GetMapping("/getCertification")
    public Collection<Certification> getCertification(@RequestParam("id") Long id){
        return repairService.getCertification(id);
    }
}
