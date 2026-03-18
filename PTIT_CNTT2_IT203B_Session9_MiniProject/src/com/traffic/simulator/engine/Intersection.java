package com.traffic.simulator.engine;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.PriorityVehicle;
import com.traffic.simulator.entity.vehicle.Vehicle;
import com.traffic.simulator.exception.CollisionException;
import com.traffic.simulator.exception.TrafficJamException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Intersection {
    private final Semaphore semaphore;
    private final LinkedBlockingQueue<Vehicle> waitingQueue;
    private final int MAX_QUEUE_SIZE;

    private TrafficLight trafficLight;


    public Intersection(TrafficLight trafficLight, int maxVehicles, int maxQueueSize) {
        this.trafficLight = trafficLight;
        this.semaphore = new Semaphore(maxVehicles);
        this.waitingQueue = new LinkedBlockingQueue<>();
        this.MAX_QUEUE_SIZE = maxQueueSize;
    }

    public void enter(Vehicle vehicle)  throws TrafficJamException, CollisionException {
        try {
            if (vehicle instanceof PriorityVehicle) {
                System.out.println("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " ( ưu tiên ) đi ngay .");
                semaphore.acquireUninterruptibly();
                return;
            }

            if (trafficLight.isGreen()){
                semaphore.acquire();
                System.out.println("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " đi qua ngã tư.");
            } else {
                if (waitingQueue.size() >= MAX_QUEUE_SIZE){
                    throw new TrafficJamException("Lên món đặc sản Hà Lội ( Kẹt xe )");
                }
                waitingQueue.offer(vehicle);
                System.out.println("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " dừng chờ đèn đỏ .");
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new CollisionException("Thread bị interrupt → nguy cơ lỗi");
        }
    }

    public void exit(Vehicle vehicle){
        semaphore.release();
        System.out.println("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " đã rời khỏi ngã tư .");
    }

    public void releaseWaitingVehicles(){
        while(!waitingQueue.isEmpty() && semaphore.availablePermits()>0){
            Vehicle vehicle = waitingQueue.poll();
            if (vehicle != null){
                try {
                    semaphore.acquire();
                    System.out.println("[" + vehicle.getId() + "] ["+vehicle.getVehicleType() + "] " + " được phép đi qua .");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
