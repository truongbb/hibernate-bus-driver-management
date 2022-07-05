package com.github.truongbb.hibernate.bus.driver.management.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "BUS_DRIVER")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUS_DRIVER_SEQ")
    @SequenceGenerator(name = "BUS_DRIVER_SEQ", sequenceName = "BUS_DRIVER_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(name = "PHONE_NUMBER", nullable = false)
    String phoneNumber;

    @Column(name = "DRIVER_LEVEL", nullable = false)
    String driverLevel;

}
