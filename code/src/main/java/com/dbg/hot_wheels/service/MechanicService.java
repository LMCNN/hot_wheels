package com.dbg.hot_wheels.service;

import com.dbg.hot_wheels.entity.Certification;
import com.dbg.hot_wheels.entity.Mechanic;
import com.dbg.hot_wheels.repository.CertificationRepository;
import com.dbg.hot_wheels.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MechanicService {
    @Autowired
    MechanicRepository mechanicRepository;

    @Autowired
    CertificationRepository certificationRepository;

    /**
     * Get all mechanics
     * @return a collection of mechanics
     */
    public Collection<Mechanic> getMechanics(){
        return mechanicRepository.findAll();
    }

    /**
     * Get all certification of a mechanic
     * @param id id of the mechanic
     * @return a collection of certification
     */
    public Collection<Certification> getMeCertification(Long id){
        Optional<Mechanic> optionalMechanic = mechanicRepository.findById(id);
        Mechanic mechanic = optionalMechanic.get();
        return mechanic.getCertifications();
    }
}
