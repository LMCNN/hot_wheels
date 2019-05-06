package com.dbg.hot_wheels.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id", sequenceName = "customer_id", allocationSize = 1)
    @GeneratedValue(generator = "customer_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private List<TelNumber> numbers = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private List<Car> cars = new LinkedList<>();
}
