package cw3;

public class Customer {
	private int currentFloor = 0;
	private int destinationFloor = 0;
	private static int customerCount = 0;
	private int id = 0;
	private Boolean inElevator = false;
	private Boolean finished = false;
	
	// Customer constructor:
	// - Takes as arguments: current floor and destination floor.
	// - Sets the currentFloor and destinationFloor.
	// - Increments the customerCount.
	// - Sets the id.
	public Customer(int currentFloor, int destinationFloor) {
		setCurrentFloor(currentFloor);
		setDestinationFloor(destinationFloor);
		customerCount++;
		setId(customerCount);
	}
	
	// Getters and Setters START
	public int getCurrentFloor() {
		return currentFloor;
	}

	private void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	private void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public Boolean isInElevator() {
		return inElevator;
	}

	public void setInElevator(Boolean inElevator) {
		this.inElevator = inElevator;
	}

	public Boolean isFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	// Getters and Setters END
	
	// This method infers the Customer's desired direction by comparing the destinationFloor and currentFloor, and returns it to the calling method as a String.
	// If the Customer wants to get off at the same floor he got on, the method returns "undetermined". 
	public String calcDirection() {
		if(destinationFloor > currentFloor) {
			return "up";
		}
		if(destinationFloor < currentFloor) {
			return "down";
		}
		return "undetermined";
	}
	
	@Override
	public String toString() {
		String objString = "";
//		private String id = null;
//		private Boolean inElevator = false;
//		private Boolean finished = false;
		objString += "id: " + id + "\ncurrentFloor: " + currentFloor + "\ndestinationFloor: " + destinationFloor
				+ "\ncalcDirection(): " + calcDirection() + "\n\n";
		return objString;
	}
}