package cw3;

public class Customer {
	private int currentFloor = 0;
	private int destinationFloor = 0;
	private String id = null;
	private Boolean inElevator = false;
	private Boolean finished = false;
	
	public Customer(int currentFloor, int destinationFloor) {
		setCurrentFloor(currentFloor);
		setDestinationFloor(destinationFloor);
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		if(currentFloor >= 0) {
			this.currentFloor = currentFloor;
		}
		else {
			System.out.println("\nError: Customer cannot get on from a negative floor.");
		}
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		if(destinationFloor >= 0) {
			this.destinationFloor = destinationFloor;
		}
		else {
			System.out.println("\nError: Customer cannot get off at a negative floor.");
		}
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
	
	@Override
	public String toString() {
		String objString = "";
//		private String id = null;
//		private Boolean inElevator = false;
//		private Boolean finished = false;
		objString += "currentFloor: " + currentFloor + "\ndestinationFloor: " + destinationFloor + "\nid: " + id + "\ninElevator: " + inElevator + "\nfinished: " + finished + "\n\n";
		return objString;
	}
}