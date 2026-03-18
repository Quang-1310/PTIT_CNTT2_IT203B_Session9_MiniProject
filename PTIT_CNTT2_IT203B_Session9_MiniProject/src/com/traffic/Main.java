package com.traffic;

import com.traffic.simulator.engine.Intersection;
import com.traffic.simulator.engine.SimulationEngine;
import com.traffic.simulator.engine.TrafficLight;
import com.traffic.simulator.monitor.TrafficMonitor;
import com.traffic.simulator.util.SimulationConfig;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        TrafficMonitor monitor = new TrafficMonitor();

        Intersection intersection = new Intersection(
                light,
                SimulationConfig.INTERSECTION_CAPACITY,
                SimulationConfig.MAX_VEHICLES_IN_QUEUE
        );

        SimulationEngine engine = new SimulationEngine(
                light, intersection, monitor,
                Executors.newCachedThreadPool(),
                Executors.newScheduledThreadPool(1)
        );

        engine.start();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            System.out.println("Luồng mô phỏng bị lỗi");
        }

        engine.stop();
    }
}
