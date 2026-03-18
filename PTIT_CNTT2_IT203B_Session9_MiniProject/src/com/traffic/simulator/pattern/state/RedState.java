package com.traffic.simulator.pattern.state;

import com.traffic.simulator.engine.TrafficLight;
import com.traffic.simulator.util.LogUtil;
import com.traffic.simulator.util.SimulationConfig;

public class RedState implements TrafficLightState {
    public RedState() {
        LogUtil.log("Trạng thái đèn [ĐỎ] đã được kích hoạt.");
    }

    @Override
    public void handle(TrafficLight context) {
        context.changeState(new GreenState());
    }

    @Override
    public String getColorName() {
        return "RED";
    }

    @Override
    public int getDurationSeconds() {
        return SimulationConfig.RED_LIGHT_DURATION;
    }

    @Override
    public boolean canVehiclePass() {
        return false;
    }
}
