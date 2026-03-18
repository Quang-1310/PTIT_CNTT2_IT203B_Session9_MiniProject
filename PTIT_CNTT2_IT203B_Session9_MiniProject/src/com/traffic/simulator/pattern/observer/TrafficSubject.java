package com.traffic.simulator.pattern.observer;

public interface TrafficSubject {
    void subscribe(TrafficObserver observer);
    void unsubscribe(TrafficObserver observer);
    void notifyObservers();
}
