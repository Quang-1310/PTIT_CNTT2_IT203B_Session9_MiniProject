package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.engine.Intersection;

public class StandardVehicle extends Vehicle {

    private Intersection intersection;
    protected long crossTime;

    public StandardVehicle(String id, double speed, VehicleType vehicleType,
                           Intersection intersection, long crossTime) {
        super(id, speed, 1, vehicleType); // xe thường có priority = 1
        this.intersection = intersection;
        this.crossTime = crossTime;
    }

    @Override
    public void move() {
        try {
            // 1. Di chuyển đến ngã tư
            System.out.println("Xe " + getId() + " đang di chuyển đến ngã tư");
            Thread.sleep((long) (1000 / getSpeed()));

            // 2. Xin vào giao lộ (Intersection)
            intersection.enter(this);

            // 3. Nếu được phép đi qua
            System.out.println("Xe " + getId() + " đang đi qua ngã tư");
            Thread.sleep(crossTime);

            // 4. Rời giao lộ
            intersection.exit(this);

        } catch (Exception e) {
            System.out.println("Lỗi xe " + getId() + ": " + e.getMessage());
        }
    }

}
