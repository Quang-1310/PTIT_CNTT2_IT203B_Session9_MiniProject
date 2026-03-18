package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public class Truck extends StandardVehicle {
    public Truck(String id, Intersection intersection) {
        super(id, 1.0, VehicleType.TRUCK, intersection, 3000);
    }
}