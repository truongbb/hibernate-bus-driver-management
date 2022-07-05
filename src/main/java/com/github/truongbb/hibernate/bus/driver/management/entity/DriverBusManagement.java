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
@Table(name = "DRIVER_BUS_MANAGEMENT")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(DriverBusManagementPrimaryKey.class)
public class DriverBusManagement implements Serializable {

    @Id
    @ManyToOne(targetEntity = Driver.class)
    Driver driver;

    @Id
    @ManyToOne(targetEntity = BusLine.class)
    BusLine busLine;

    @Column(name = "ROUND_NUMBER", nullable = false)
    Integer roundNumber;

}
