package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public class Motorbike extends StandardVehicle {
    public Motorbike(String id, Intersection in) {
        super(id, 3.0, VehicleType.MOTORBIKE, in, 1000);
    }
}