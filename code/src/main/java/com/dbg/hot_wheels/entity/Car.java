package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cars")
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Car {

    @Id
    @SequenceGenerator(name = "car_id", sequenceName = "car_id", allocationSize = 1)
    @GeneratedValue(generator = "car_id")
    private Long id;

    @Column
    private String year;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Long customer_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable=false, updatable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<MaintenanceRecord> maintenanceRecords;
}
