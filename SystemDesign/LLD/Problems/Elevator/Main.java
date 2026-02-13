package SystemDesign.LLD.Problems.Elevator;

import java.util.*;

enum Direction {
    UP, DOWN, IDLE
}

enum ElevatorStatus {
    MOVING, STOPPED, IDLE, DOOR_OPEN
}

enum DoorStatus {
    OPEN, CLOSED
}

abstract class SchedulerStrategy {
    Elevator assignElevator(Elevator[] elevators, int floor, Direction direction);
}

class Floor {
    private int floorNumber;
    private ElevatorSystem system;

    public Floor(int floorNumber, ElevatorSystem system) {
        this.floorNumber = floorNumber;
        this.system = system;
    }

    public void pressButton(Direction direction) {
        system.requestElevator(floorNumber, direction);
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}

class NearestCarScheduler implements SchedulerStrategy {

    @Override
    public Elevator assignElevator(Elevator[] elevators, int floor, Direction direction) {
        Elevator best = null;
        int minScore = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - floor);

            boolean compatible = e.getDirection() == Direction.IDLE ||
                    (e.getDirection() == direction &&
                            ((direction == Direction.UP && floor >= e.getCurrentFloor()) ||
                                    (direction == Direction.DOWN && floor <= e.getCurrentFloor())));

            int score = compatible ? distance : distance + 1000;

            if (score < minScore) {
                minScore = score;
                best = e;
            }
        }
        return best;
    }
}

class Door {
    private DoorStatus doorStatus = DoorStatus.CLOSED;

    public void open() {
        this.doorStatus = DoorStatus.OPEN;
    }

    public void close() {
        this.doorStatus = DoorStatus.CLOSED;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }
}

public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(3, 10);

        system.getFloor(3).pressButton(Direction.UP);
        system.getFloor(7).pressButton(Direction.DOWN);

        for (int t = 0; t < 8; t++) {
            System.out.println("---- Tick " + t + " ----");
            system.stepSimulation();
            system.getSystemStatus();
            system.getFloor(t).pressButton(Direction.UP);
            system.getFloor(t).pressButton(Direction.DOWN);
        }
    }
}
