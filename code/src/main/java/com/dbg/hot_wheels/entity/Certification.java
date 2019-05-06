package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Certification")
@Data
public class Certification {
    @Id
    @SequenceGenerator(name = "certification_id", sequenceName = "certification_id", allocationSize = 1)
    @GeneratedValue(generator = "certification_id")
    private Long id;

    @Column
    private String description;

    @ManyToMany(mappedBy = "certifications")
    @JsonIgnore
    private List<Mechanic> mechanics;

    @OneToMany(mappedBy = "certification")
    @JsonIgnore
    private List<RepairType> repairTypes;
}
