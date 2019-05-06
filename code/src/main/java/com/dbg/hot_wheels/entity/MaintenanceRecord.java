package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "maintenance_record")
@Data
public class MaintenanceRecord {

    @Id
    @SequenceGenerator(name = "maintenance_id", sequenceName = "maintenance_id", allocationSize = 1)
    @GeneratedValue(generator = "maintenance_id")
    private Long Id;

    @Column
    private String record_date;

    @Column
    private Long car_id;

    @Column
    private Long repair_id;

    @Column
    private Long mechanic_id;

    @ManyToOne
    @JoinColumn(name = "car_id", insertable=false, updatable = false)
    @JsonIgnore
    private Car car;

    @ManyToOne
    @JoinColumn(name = "repair_id", insertable=false, updatable = false)
    @JsonIgnore
    private RepairType repairType;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", insertable=false, updatable = false)
    @JsonIgnore
    private Mechanic mechanic;
}
