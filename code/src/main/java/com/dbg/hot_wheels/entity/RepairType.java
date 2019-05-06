package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "RepairType.toList",
                query = "SELECT r.id as id, r.description as description, r.hours as hours, c.description as certification FROM repair_type r INNER JOIN certification c ON r.certification_id = c.id",
                resultClass = RepairType.class
        )
})

@Entity
@Table(name = "RepairType")
@Data
public class RepairType {
    @Id
    @SequenceGenerator(name = "repair_id", sequenceName = "repair_id", allocationSize = 1)
    @GeneratedValue(generator = "repair_id")
    private Long id;

    @Column
    private String description;

    @Column
    private Integer hours;

    @Column
    private Long certification_id;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable=false, updatable = false)
    @JsonIgnore
    private Certification certification;

    @ManyToMany
    @JsonIgnore
    private List<Part> parts;

    @OneToMany(mappedBy = "repairType")
    @JsonIgnore
    private List<MaintenanceRecord> maintenanceRecords;
}
