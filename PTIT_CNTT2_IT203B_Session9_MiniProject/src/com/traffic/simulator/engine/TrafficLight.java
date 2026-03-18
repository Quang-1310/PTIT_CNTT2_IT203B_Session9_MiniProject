package com.traffic.simulator.engine;

import com.traffic.simulator.pattern.observer.TrafficObserver;
import com.traffic.simulator.pattern.observer.TrafficSubject;
import com.traffic.simulator.pattern.state.GreenState;
import com.traffic.simulator.pattern.state.TrafficLightState;
import com.traffic.simulator.util.LogUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrafficLight implements TrafficSubject, Runnable {
    private TrafficLightState currentState;
    private List<TrafficObserver> observers = new CopyOnWriteArrayList<>();

    public TrafficLight() {
        this.currentState = new GreenState();
    }

    public TrafficLightState getCurrentState() {
        return currentState;
    }

    // ================= Observer =================
    @Override
    public void subscribe(TrafficObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(TrafficObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (TrafficObserver observer : observers) {
            observer.onTrafficLightChanged(currentState);
        }
    }

    // ================= State =================
    public void changeState(TrafficLightState newState) {
        this.currentState = newState;
        LogUtil.log("Changed to " + currentState.getColorName());
        notifyObservers();
    }

    public boolean isGreen() {
        return currentState.canVehiclePass();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int duration = currentState.getDurationSeconds();
                Thread.sleep(duration);

                currentState.handle(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
