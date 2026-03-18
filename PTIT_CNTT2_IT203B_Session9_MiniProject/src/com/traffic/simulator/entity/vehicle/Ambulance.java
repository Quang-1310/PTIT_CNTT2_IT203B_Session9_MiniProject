package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public class Ambulance extends PriorityVehicle {
    public Ambulance(String id, Intersection in) {
        super(id, 4.0, VehicleType.AMBULANCE, in, 1000);
    }
    @Override
    public void activateSiren() {
        System.out.println("🚑 [SIREN] " + getId() + ": WOO-WOO!");
    }
}