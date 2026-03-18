package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;

public abstract class Vehicle implements Runnable {

    private String id;
    private double speed;
    private int priority;
    private VehicleType vehicleType;

    public Vehicle(String id, double speed, int priority, VehicleType vehicleType) {
        this.id = id;
        this.speed = speed;
        this.priority = priority;
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public int getPriority() {
        return priority;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public abstract void move();

    @Override
    public void run() {
        move();
    }
}
