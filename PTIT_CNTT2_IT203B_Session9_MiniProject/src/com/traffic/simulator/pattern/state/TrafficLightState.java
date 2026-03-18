package com.traffic.simulator.pattern.state;

import com.traffic.simulator.engine.TrafficLight;

public interface TrafficLightState {
    void handle(TrafficLight context);
    String getColorName();
    int getDurationSeconds();
    boolean canVehiclePass();
}
