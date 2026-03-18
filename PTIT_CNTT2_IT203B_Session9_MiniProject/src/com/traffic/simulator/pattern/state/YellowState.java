package com.traffic.simulator.pattern.state;

import com.traffic.simulator.engine.TrafficLight;
import com.traffic.simulator.util.LogUtil;
import com.traffic.simulator.util.SimulationConfig;

public class YellowState implements TrafficLightState {
    public YellowState() {
        LogUtil.log("Trạng thái đèn [VÀNG] đã được kích hoạt.");
    }

    @Override
    public void handle(TrafficLight context) {
        context.changeState(new RedState());
    }

    @Override
    public String getColorName() {
        return "YELLOW";
    }

    @Override
    public int getDurationSeconds() {
        return SimulationConfig.YELLOW_LIGHT_DURATION;
    }

    @Override
    public boolean canVehiclePass() {
        return false;
    }
}
