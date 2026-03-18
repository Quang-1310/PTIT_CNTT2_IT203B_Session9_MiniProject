package com.traffic.simulator.util;

import com.traffic.simulator.entity.VehicleType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static synchronized void log(String message) {
        System.out.println("[Time: " + LocalTime.now().format(formatter) + "] " + message);
    }
}
