package SystemDesign.LLD.Problems.Elevator;

import java.util.TreeSet;

class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorStatus status;
    private TreeSet<Integer> requests;
    private Door door;

    public Elevator(int id, int startFloor) {
        this.id = id;
        this.currentFloor = startFloor;
        this.direction = Direction.IDLE;
        this.status = ElevatorStatus.IDLE;
        this.requests = new TreeSet<>();
        this.door = new Door();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isIdle() {
        return requests.isEmpty();
    }

    public void addRequest(int floor) {
        requests.add(floor);
        if (direction == Direction.IDLE) {
            direction = (floor > currentFloor) ? Direction.UP : Direction.DOWN;
        }
    }

    public void move() {
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            status = ElevatorStatus.IDLE;
            return;
        }

        status = ElevatorStatus.MOVING;

        Integer target = (direction == Direction.UP)
                ? requests.ceiling(currentFloor)
                : requests.floor(currentFloor);

        if (target == null) {
            direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;
            return;
        }

        if (currentFloor < target) {
            currentFloor++;
        } else if (currentFloor > target) {
            currentFloor--;
        }

        if (currentFloor == target) {
            stopAtFloor(target);
        }
    }

    private void stopAtFloor(int floor) {
        status = ElevatorStatus.STOPPED;
        requests.remove(floor);
        openDoor();
        closeDoor();

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
        }
    }

    public void openDoor() {
        door.open();
        status = ElevatorStatus.DOOR_OPEN;
    }

    public void closeDoor() {
        door.close();
        status = ElevatorStatus.MOVING;
    }

    public ElevatorStatus getCurrentState() {
        return status;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", floor=" + currentFloor +
                ", direction=" + direction +
                ", status=" + status +
                ", pending=" + requests +
                '}';
    }
}