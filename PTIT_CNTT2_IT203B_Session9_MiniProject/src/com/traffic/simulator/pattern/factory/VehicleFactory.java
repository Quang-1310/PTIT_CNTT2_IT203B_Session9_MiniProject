package com.traffic.simulator.pattern.factory;

import com.traffic.simulator.engine.Intersection;
import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.*;

import java.util.Random;

public class VehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createRandom(String id, Intersection intersection) {
        int chance = random.nextInt(100);
        if (chance < 40) {
            return new Motorbike(id,intersection);
        } else if (chance < 75) {
            return new Car(id,intersection);
        } else if (chance < 90) {
            return new Truck(id,intersection);
        } else {
            return new Ambulance(id,intersection);
        }
    }

    public static Vehicle crate(VehicleType type, String id, Intersection intersection) {
        switch (type) {
            case MOTORBIKE: {
                return new Motorbike(id,intersection);
            }
            case CAR: {
                return new Car(id,intersection);
            }
            case TRUCK: {
                return new Truck(id,intersection);
            }
            case AMBULANCE: {
                return new Ambulance(id,intersection);
            }
            default:
                throw new IllegalArgumentException("Xe khono hp le");
        }
    }
}