package com.traffic.simulator.engine;


import com.traffic.simulator.entity.vehicle.Vehicle;
import com.traffic.simulator.exception.CollisionException;
import com.traffic.simulator.exception.TrafficJamException;
import com.traffic.simulator.monitor.TrafficMonitor;
import com.traffic.simulator.pattern.factory.VehicleFactory;
import com.traffic.simulator.util.LogUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final TrafficLight trafficLight;
    private final Intersection intersection;
    private final TrafficMonitor monitor;

    private volatile boolean isRunning = false;

    private static final int VEHICLE_SPAWN_RATE = 2;

    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduler;
    Random random = new Random();


    public SimulationEngine(TrafficLight trafficLight, Intersection intersection, TrafficMonitor monitor, ExecutorService executorService, ScheduledExecutorService scheduler) {
        this.trafficLight = trafficLight;
        this.intersection = intersection;
        this.monitor = monitor;
        this.executorService = Executors.newCachedThreadPool();
        this.scheduler = Executors.newScheduledThreadPool(1);
    }


    public void start (){
        isRunning = true;


        LogUtil.log("🚦Bắt đầu quá trình mô phỏng");
        Thread lightThread = new Thread(trafficLight);
        lightThread.setDaemon(true);
        lightThread.start();
        scheduler.scheduleAtFixedRate(() -> {
            if (!isRunning) return;

            try {
                String id = "VH" + random.nextInt(10000);

                Vehicle vehicle = VehicleFactory.createRandom(id,intersection);

                LogUtil.log("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " được tạo");

                executorService.submit(() -> processVehicle(vehicle));

            } catch (Exception e) {
                LogUtil.log("Lỗi khi spawn xe: " + e.getMessage());
            }

        }, 0, VEHICLE_SPAWN_RATE, TimeUnit.SECONDS);
    }

    private void processVehicle(Vehicle vehicle) {
        try {
            vehicle.run();

            monitor.recordVehiclePassed(vehicle);
        } catch (TrafficJamException e) {
            monitor.recordJam();
            LogUtil.log("Rào chắn: " + e.getMessage());

        } catch (CollisionException e) {
            LogUtil.log("Tai nạn: " + e.getMessage());

        } catch (Exception e) {
            LogUtil.log("Lỗi: " + e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;

        LogUtil.log("🛑 Kết thúc quá trình mô phỏng...");

        scheduler.shutdown();
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }

            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }

        } catch (InterruptedException e) {
            executorService.shutdownNow();
            scheduler.shutdownNow();
        }

        monitor.printReport();
    }
}
