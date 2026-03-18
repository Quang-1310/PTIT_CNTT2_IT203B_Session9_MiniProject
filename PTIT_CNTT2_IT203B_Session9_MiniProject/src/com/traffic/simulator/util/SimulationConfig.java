package com.traffic.simulator.util;

public class SimulationConfig {
    public static final int GREEN_LIGHT_DURATION = 10000; // 10 giây
    public static final int RED_LIGHT_DURATION = 10000;   // 10 giây
    public static final int YELLOW_LIGHT_DURATION = 3000; // 3 giây

    public static final int MAX_VEHICLES_IN_QUEUE = 50;   // Giới hạn gây kẹt xe
    public static final int INTERSECTION_CAPACITY = 2;    // Số xe tối đa trong ngã tư cùng lúc
}
