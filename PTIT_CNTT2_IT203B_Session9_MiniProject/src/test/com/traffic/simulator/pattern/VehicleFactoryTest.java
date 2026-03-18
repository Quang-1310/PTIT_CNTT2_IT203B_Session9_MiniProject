package com.traffic.simulator.pattern;

import com.traffic.simulator.engine.Intersection;
import com.traffic.simulator.engine.TrafficLight;
import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.Ambulance;
import com.traffic.simulator.entity.vehicle.Car;
import com.traffic.simulator.entity.vehicle.Vehicle;
import com.traffic.simulator.pattern.factory.VehicleFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleFactoryTest {
    private final VehicleFactory factory = new VehicleFactory();

    @Test
    void testCreateSpecificVehicle() {
        TrafficLight light = new TrafficLight();
        Intersection intersection = new Intersection(light, 5, 5);
        Vehicle car = factory.crate(VehicleType.CAR, "C-01", intersection);

        assertNotNull(car);
        assertTrue(car instanceof Car);
        assertEquals(2, car.getSpeed());
        assertEquals(1, car.getPriority());
    }

    @Test
    void testCreateRandomNotNull() {
        TrafficLight light = new TrafficLight();
        Intersection intersection = new Intersection(light, 5, 5);
        assertNotNull(factory.createRandom("R-99", intersection));
    }

    @Test
    void testAmbulanceDistribution() {
        int iterations = 1000;
        int ambulanceCount = 0;
        TrafficLight light = new TrafficLight();
        Intersection intersection = new Intersection(light, 5, 5);

        for (int i = 0; i < iterations; i++) {
            Vehicle v = factory.createRandom("ID-" + i, intersection);
            if (v instanceof Ambulance) {
                ambulanceCount++;
            }
        }

        assertTrue(ambulanceCount >= 50 && ambulanceCount <= 150, "Tỷ lệ Ambulance không khớp: " + ambulanceCount);

        // Test xe cứu thương phải có priority cao
        Vehicle amb = factory.crate(VehicleType.AMBULANCE, "A-01", intersection);
        assertEquals(0, amb.getPriority());
    }
}
