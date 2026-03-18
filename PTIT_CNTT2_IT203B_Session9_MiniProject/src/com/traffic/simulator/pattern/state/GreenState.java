package com.traffic.simulator.pattern.state;

import com.traffic.simulator.engine.TrafficLight;
import com.traffic.simulator.util.LogUtil;
import com.traffic.simulator.util.SimulationConfig;

public class GreenState implements TrafficLightState {
    public GreenState() {
        LogUtil.log("Trạng thái đèn xanh đã được kích hoạt.");
    }

    @Override
    public void handle(TrafficLight context) {

        context.changeState(new YellowState());
    }

    @Override
    public String getColorName() {
        return "GREEN";
    }

    @Override
    public int getDurationSeconds() {
        return SimulationConfig.GREEN_LIGHT_DURATION;
    }

    @Override
    public boolean canVehiclePass() {
        return true;
    }
}
