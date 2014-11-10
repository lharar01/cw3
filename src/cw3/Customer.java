package cw3;

public class Customer {
	private int currentFloor = 0;
	private int destinationFloor = 0;
	private String id = null;
	private Boolean inElevator = false;
	private Boolean finished = false;
	
	public Customer(int currentFloor, int destinationFloor, String id, Boolean inElevator, Boolean finished) {
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.id = id;
		this.inElevator = inElevator;
		this.finished = finished;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getInElevator() {
		return inElevator;
	}

	public void setInElevator(Boolean inElevator) {
		this.inElevator = inElevator;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
}