package com.traffic.simulator.monitor;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.Vehicle;
import com.traffic.simulator.util.LogUtil;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TrafficMonitor {


    private final AtomicInteger totalPassed = new AtomicInteger(0);
    private final AtomicInteger jamCount = new AtomicInteger(0);
    private final ConcurrentHashMap<VehicleType, AtomicInteger> vehicleStats = new ConcurrentHashMap<>();

    public TrafficMonitor() {
        for (VehicleType type : VehicleType.values()) {
            vehicleStats.put(type, new AtomicInteger(0));
        }
    }

    // Ghi nhận xe đi qua
    public void recordVehiclePassed(Vehicle v) {
        totalPassed.incrementAndGet();
        vehicleStats.get(v.getVehicleType()).incrementAndGet();

        LogUtil.log(v.getId() + " đã qua giao lộ");
    }

    // Ghi nhận kẹt xe
    public void recordJam() {
        jamCount.incrementAndGet();
        LogUtil.log("⚠️ Kẹt xe xảy ra!");
    }


    public void printReport(){
        System.out.println("\n===== TRAFFIC REPORT =====");
        System.out.println("Tổng xe đã qua: " + totalPassed.get());
        System.out.println("Số lần kẹt xe: " + jamCount.get());

        System.out.println("\n--- Thống kê theo loại xe ---");

        vehicleStats.entrySet()
                .stream()
                .sorted((e1,e2) -> e1.getValue().get() - e2.getValue().get())
                .forEach(entry -> System.out.println(
                        entry.getKey() + ": " + entry.getValue().get()
                ));
    }
}
