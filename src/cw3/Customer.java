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
	  * </ul>
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
	
	/**
	 * Sets this customer's {@link #currentFloor}.
	 * 
	 * @param currentFloor  The floor this customer is on.
	 */
	private void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * Returns this customer's destination floor.
	 * 
	 * @return  destinationFloor
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * Sets this customer's {@link #destinationFloor}.
	 * 
	 * @param  destinationFloor  The floor this customer wishes to get to.
	 */
	private void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * Returns this customer's unique id.
	 * 
	 * @return  id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets this customer's {@link #id}.
	 * 
	 * @param  id  This customer's unique id
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns whether or not this customer is currently in the elevator.
	 * 
	 * @return  inElevator
	 */
	public Boolean isInElevator() {
		return inElevator;
	}

	/**
	 * Sets this customer's {@link #inElevator}.
	 * 
	 * @param  inElevator  whether or not this customer is in the elevator
	 */
	public void setInElevator(Boolean inElevator) {
		this.inElevator = inElevator;
	}

	/**
	 * Returns whether or not this customer has been served by the elevator.
	 * 
	 * @return  finished
	 */
	public Boolean isFinished() {
		return finished;
	}

	/**
	 * Sets this customer's {@link #finished}
	 * 
	 * @param  finished  whether or not this customer has been served by the elevator
	 */
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	// Getters and Setters END
	
	 /**
	  * Infers the Customer's desired direction by comparing the destinationFloor and currentFloor,
	  * and returns it as a String.
	  * If the Customer wants to get off at the same floor he got on, the method returns "undetermined".
	  *  
	  * @return  the customer's desired direction
	  */
	public String calcDirection() {
		if(destinationFloor > currentFloor) {
			return "up";
		}
		if(destinationFloor < currentFloor) {
			return "down";
		}
		return "undetermined";
	}
	
	/**
	 * Returns a summary of this customer's properties in a readable format.
	 * 
	 * @return  objString
	 */
	@Override
	public String toString() {
		String objString = "";
		objString += "id: " + id + "\ncurrentFloor: " + currentFloor + "\ndestinationFloor: " + destinationFloor
				+ "\ncalcDirection(): " + calcDirection() + "\n\n";
		return objString;
	}
}