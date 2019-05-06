package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Part")
@Data
public class Part {
    @Id
    @SequenceGenerator(name = "part_id", sequenceName = "part_id", allocationSize = 1)
    @GeneratedValue(generator = "part_id")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer cost;

    @ManyToMany(mappedBy = "parts")
    @JsonIgnore
    private List<RepairType> repairTypes;
}
