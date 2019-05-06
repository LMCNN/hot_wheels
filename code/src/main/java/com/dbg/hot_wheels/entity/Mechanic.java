package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Mechanic")
@Data
public class Mechanic {
    @Id
    @SequenceGenerator(name = "mechanic_id", sequenceName = "mechanic_id", allocationSize = 1)
    @GeneratedValue(generator = "mechanic_id")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer hourly_rate;

    @ManyToMany
    @JsonIgnore
    private List<Certification> certifications;

    @OneToMany(mappedBy = "mechanic")
    @JsonIgnore
    private List<MaintenanceRecord> maintenanceRecords;
}
