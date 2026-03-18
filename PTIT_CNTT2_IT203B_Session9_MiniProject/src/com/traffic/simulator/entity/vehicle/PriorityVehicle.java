package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public abstract class PriorityVehicle extends Vehicle {
    protected Intersection intersection;
    protected long crossTime;

    public PriorityVehicle(String id, double speed, VehicleType vehicleType, Intersection intersection, long crossTime) {
        // Xe ưu tiên mặc định priority = 0
        super(id, speed, 0, vehicleType);
        this.intersection = intersection;
        this.crossTime = crossTime;
    }

    public abstract void activateSiren();

    @Override
    public void move() {
        try {
            // 1. Hú còi báo hiệu
            activateSiren();

            // 2. Di chuyển đến ngã tư dựa trên tốc độ
            Thread.sleep((long) (1000 / getSpeed()));

            // 3. Vào ngã tư
            intersection.enter(this);

            // 4. Thời gian chiếm dụng ngã tư
            Thread.sleep(crossTime);

            // 5. Rời ngã tư
            intersection.exit(this);
        } catch (Exception e) {
            System.err.println("Lỗi xe ưu tiên " + getId() + ": " + e.getMessage());
        }
    }
}