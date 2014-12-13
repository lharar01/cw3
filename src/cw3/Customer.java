package cw3;

/**
 * This class denotes an elevator customer.
 * <p>It is used by the AppDriver class to simulate elevator operation.
 * 
 * @author Liran Harary &amp; Shay Meshulam
 * @version 1.0
 * @since 10th November 2014
 */
public class Customer {
	/** The number of customers that were created by this class thus far */
	private static int customerCount = 0;
	
	/** The floor this customer is on */
	private int currentFloor = 0;
	
	/** This customer's destination floor */
	private int destinationFloor = 0;
	
	
	/** This customer's id. Starts from 0 and incremented by 1 with each Customer instantiation */
	private int id = 0;
	
	/** Whether or not the customer is currently in the elevator */
	private Boolean inElevator = false;
	
	/** Whether or not the customer has been served */
	private Boolean finished = false;
	
	 /**
	  * <ul>
	  * <li>Takes as arguments: current floor and destination floor.</li>
	  * <li>Sets the {@link #currentFloor} and {@link #destinationFloor}.</li>
	  * <li>Increments the {@link #customerCount}.</li>
	  * <li>Sets the {@link #id}.</li>
	  * 
	  * @param currentFloor  This customer's current floor
	  * @param destinationFloor  This customer's destination floor
	  */
	public Customer(int currentFloor, int destinationFloor) {
		setCurrentFloor(currentFloor);
		setDestinationFloor(destinationFloor);
		customerCount++;
		setId(customerCount);
	}
	
	// Getters and Setters START
	/**
	 * Returns the floor this customer is on.
	 * 
	 * @return currentFloor
	 */
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
	
	// Infers the Customer's desired direction by comparing the destinationFloor and currentFloor, and returns it to the calling method as a String.
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
		objString += "id: " + id + "\ncurrentFloor: " + currentFloor + "\ndestinationFloor: " + destinationFloor
				+ "\ncalcDirection(): " + calcDirection() + "\n\n";
		return objString;
	}
}