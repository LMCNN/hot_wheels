package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "telnumber")
@IdClass(TelNumberId.class)
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TelNumber {

    @Id
    @Column(name = "customer_id")
    private Long customer_id;

    @Id
    @Column(name = "tel_number")
    private String tel_number;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable=false, updatable = false)
    @JsonIgnore
    private Customer customer;
}
