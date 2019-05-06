package com.dbg.hot_wheels.controller;

import com.dbg.hot_wheels.entity.Estimate;
import com.dbg.hot_wheels.repository.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class EstimateController {
    @Autowired
    EstimateRepository estimateRepository;

    @GetMapping("/estimate")
    public Collection<Estimate> estimateRepair(@RequestParam("type") String type) {
        Collection<Estimate> estimates = estimateRepository.estimateRepair(type);
        System.out.println(estimates);
        return estimates;
    }
}
