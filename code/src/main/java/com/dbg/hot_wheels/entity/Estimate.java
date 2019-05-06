package com.dbg.hot_wheels.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Estimate.estimateRepair",
                query = "select * from " +
                        "(select estimate.description as repair_type, estimate.name as mechanic_name, estimate.hours as hours, estimate.hourly_rate as hourly_rate, " +
                        "estimate.part_cost as part_cost, estimate.hours * estimate.hourly_rate +  estimate.part_cost as total_cost " +
                        "from (select repair_part.id as id, repair_part.description as description, m.name as name, repair_part.hours as hours, m.hourly_rate as hourly_rate, repair_part.cost as part_cost from " +
                        "(select r.id as id, r.description as description, r.hours as hours, sum(p.cost) as cost, r.certification_id as c_id " +
                        "from repair_type r inner join repair_type_parts t on r.id = t.repair_types_id inner join part p on t.parts_id = p.id  group by r.id) as repair_part " +
                        "inner join mechanic_certifications mc on repair_part.c_id = mc.certifications_id inner join mechanic m on mc.mechanics_id = m.id) as estimate) as result " +
                        "where repair_type = :type",
                resultClass = Estimate.class
        )
})

@Entity
@Data
public class Estimate {
    @Id
    private String repair_type;
    private String mechanic_name;
    private Integer hours;
    private Integer hourly_rate;
    private Integer part_cost;
    private Integer total_cost;
}
