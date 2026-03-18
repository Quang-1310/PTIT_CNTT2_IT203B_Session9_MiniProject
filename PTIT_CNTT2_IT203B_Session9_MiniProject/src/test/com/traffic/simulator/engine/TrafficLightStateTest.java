package com.traffic.simulator.engine;

import com.traffic.simulator.pattern.state.GreenState;
import com.traffic.simulator.pattern.state.RedState;
import com.traffic.simulator.pattern.state.TrafficLightState;
import com.traffic.simulator.pattern.state.YellowState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightStateTest {
    @Test
    void testStateTransitions(){
        TrafficLight light = new TrafficLight();

        // Green -> Yellow
        TrafficLightState green = new GreenState();
        green.handle(light);
        assertEquals("YELLOW", light.getCurrentState().getColorName());
        assertFalse(light.isGreen());

        // Yellow -> Red
        TrafficLightState yellow = light.getCurrentState();
        yellow.handle(light);
        assertEquals("RED", light.getCurrentState().getColorName());

        // Red -> Green
        TrafficLightState red = light.getCurrentState();
        red.handle(light);
        assertEquals("GREEN", light.getCurrentState().getColorName());
        assertTrue(light.isGreen());
    }

    @Test
    void testDurationFromConfig() {
        assertEquals(10000, new GreenState().getDurationSeconds()); //  10s
        assertEquals(3000, new YellowState().getDurationSeconds()); //  3s
        assertEquals(10000, new RedState().getDurationSeconds()); // 10s
    }

}
