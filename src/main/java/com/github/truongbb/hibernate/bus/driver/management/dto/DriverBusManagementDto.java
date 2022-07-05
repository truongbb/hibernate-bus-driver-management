package com.github.truongbb.hibernate.bus.driver.management.dto;

import com.github.truongbb.hibernate.bus.driver.management.entity.BusLine;
import com.github.truongbb.hibernate.bus.driver.management.entity.Driver;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverBusManagementDto {

    Driver driver;

    Map<BusLine, Integer> assignedBuses;

    float totalDistance;

    public DriverBusManagementDto(Driver driver, Map<BusLine, Integer> assignedBuses) {
        this.driver = driver;
        this.assignedBuses = assignedBuses;
    }

    public void setTotalDistance() {
        if (assignedBuses == null || assignedBuses.isEmpty()) {
            this.setTotalDistance(0);
        }
        AtomicReference<Float> totalDistance = new AtomicReference<>((float) 0);
        this.assignedBuses.forEach((busLine, round) -> totalDistance.updateAndGet(v -> v + busLine.getDistance() * round));
        this.totalDistance = totalDistance.get();

    }

}
