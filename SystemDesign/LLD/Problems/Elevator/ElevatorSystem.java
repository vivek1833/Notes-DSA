package SystemDesign.LLD.Problems.Elevator;

class ElevatorSystem {
    private Elevator[] elevators;
    private Floor[] floors;
    private SchedulerStrategy scheduler;

    public ElevatorSystem(int numElevators, int numFloors) {
        this.elevators = new Elevator[numElevators];
        this.floors = new Floor[numFloors];
        this.scheduler = new NearestCarScheduler();

        for (int i = 0; i < numFloors; i++) {
            floors[i] = new Floor(i, this);
        }

        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator(i, 0);
        }
    }

    public void requestElevator(int floorNumber, Direction direction) {
        Elevator assigned = scheduler.assignElevator(elevators, floorNumber, direction);
        System.out.println("Request at floor " + floorNumber + " assigned to Elevator " + assigned.getId());
        assigned.addRequest(floorNumber);
    }

    public void stepSimulation() {
        for (Elevator e : elevators) {
            e.move();
        }
    }

    public void getSystemStatus() {
        for (Elevator e : elevators) {
            System.out.println(e);
        }
    }

    public Floor getFloor(int floor) {
        return floors[floor];
    }
}