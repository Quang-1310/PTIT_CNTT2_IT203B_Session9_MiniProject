package com.traffic.simulator.pattern.observer;

import com.traffic.simulator.pattern.state.TrafficLightState;

public interface TrafficObserver {
    void onTrafficLightChanged(TrafficLightState newState);
}