package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public class Car extends StandardVehicle {
    public Car(String id, Intersection intersection) {
        super(id, 2.0, VehicleType.CAR, intersection, 2000);
    }
}