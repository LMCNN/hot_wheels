package com.dbg.hot_wheels.service;

import com.dbg.hot_wheels.entity.Certification;
import com.dbg.hot_wheels.entity.Part;
import com.dbg.hot_wheels.entity.RepairType;
import com.dbg.hot_wheels.repository.PartRepository;
import com.dbg.hot_wheels.repository.RepairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    RepairTypeRepository repairTypeRepository;

    @Autowired
    PartRepository partRepository;

    /**
     * Get all repair types from the database
     * @return a Collection of repair types
     */
    public Collection<RepairType> getRepairs()
    {
        return repairTypeRepository.findAll();
    }

    /**
     * Get part of a repair type
     * @param id the repair type if
     * @return parts need to finish the repair
     */
    public Collection<Part> getParts(Long id){
        RepairType repairType = repairTypeRepository.getOne(id);
        return repairType.getParts();
    }

    /**
     * Get certification of a repair type
     * @param id the repair type id
     * @return the certification need to finish the repair
     */
    public Collection<Certification> getCertification(Long id){
        List<Certification> result = new LinkedList<>();
        RepairType repairType = repairTypeRepository.getOne(id);
        result.add(repairType.getCertification());
        return result;
    }
}
