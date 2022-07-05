package com.github.truongbb.hibernate.bus.driver.management.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class DriverBusManagementPrimaryKey implements Serializable {

    Long driverId;

    Long busLineId;

}
