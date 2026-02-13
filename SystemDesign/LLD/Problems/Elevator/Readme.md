# Elevator System Design

## Problem Statement

Design an Elevator System LLD that allows users to request elevators from different floors and select destination floors
from inside the elevator. The system should handle:

* Creating an elevator system with multiple elevators
* Requesting an elevator from a floor (Up/Down)
* Selecting destination floors inside the elevator
* Efficient scheduling and movement of elevators
* Tracking elevator status and position

## Functional Requirements

1. Initialize the elevator system with N elevators and M floors
2. Request an elevator from a floor (Up / Down)
3. Select destination floor from inside the elevator
4. Move elevators based on scheduling strategy
5. Track current state of each elevator (Idle, Moving, Door Open)
6. Get system status at any time

---

## Entities

### 1. ElevatorSystem

* elevators: List<Elevator>
* floors: List<Floor>
* scheduler: Scheduler

**Methods**

* ElevatorSystem(int numElevators, int numFloors)
* requestElevator(int floorNumber, Direction direction)
* getSystemStatus()

---

### 2. Elevator

* id
* currentFloor
* direction
* status
* requests: Set<int> (destination floors)
* door

**Methods**

* move()
* addRequest(int floor)
* openDoor()
* closeDoor()
* getCurrentState()

---

### 3. Floor

* floorNumber
* upButton
* downButton

**Methods**

* pressButton(Direction direction)
* getFloorNumber()

---

### 4. Scheduler (Strategy Component)

* elevators

**Methods**

* assignElevator(int floor, Direction direction) -> Elevator
* optimizeMovement()

*(Can implement strategies like Nearest Car, FCFS, SCAN algorithm)*

---

### 5. Request

* sourceFloor
* destinationFloor
* direction

---

### 6. Door

* status (OPEN/CLOSED)

**Methods**

* open()
* close()

---

### 7. Enums

**Direction**

* UP
* DOWN
* IDLE

**ElevatorStatus**

* MOVING
* STOPPED
* IDLE
* DOOR_OPEN

**DoorStatus**

* OPEN
* CLOSED

---

## Core Interaction Flow

1. User presses floor button → Floor creates request
2. ElevatorSystem forwards request to Scheduler
3. Scheduler selects optimal Elevator
4. Elevator moves toward requested floor
5. User selects destination
6. Elevator processes queued destinations sequentially

---
