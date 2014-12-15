package cw3;

import java.util.ArrayList;

/**
 * <p>This class denotes an American-style elevator (no 13th floor) with two strategies:</p>
 * <ul>
 * <li>Default strategy: Start at the bottom, go to the top and then back to the bottom.</li>
 * <li>Improved strategy: Start from current position and direction; change direction when finished serving customers in that
 * direction; and only go as far as necessary.</li>
 * </ul>
 * 
 * These two methods (the strategies) return the number of moves it took them to serve all customers.
 * <p>This class is used by the AppDriver class to simulate elevator operation.
 * 
 * @author Liran Harary &amp; Shay Meshulam
 * @version 1.0
 * @since 10th November 2014
 */
public class Elevator {
	/** Number of floors the elevator serves */
	private int numFloors = 0;
	
	/** Customers in the elevator */
	private ArrayList<Customer> registerList = new ArrayList<Customer>();
	
	/** Customers who called the elevator */
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	/** Elevator's current floor */
	private int currentFloor = 0;
	
	/** Elevator's top floor. Calculated by {@link #numFloors} and {@link #bottomFloor} */
	private int topFloor = 0;
	
	/** Elevator's bottom floor */
	private int bottomFloor = 0;
	
	/** Elevator's current direction */
	private String direction = "up";
	
	/** Turns annotations on or off. These are notes of what is happening in various steps of the program. */
	private boolean annotations = false;
	
	 /**
	  * <ul>
	  * <li>Takes as arguments: number of floors, bottom floor and a Customer list.</li>
	  * <li>Sets the {@link #bottomFloor}, {@link #numFloors}, {@link #currentFloor} and {@link #customerList}.</li>
	  * <li>Infers and sets the {@link #topFloor}.</li>
	  * </ul>
	  * 
	  * @param  numFloors     Number of floors this elevator serves
	  * @param  bottomFloor   Elevator's bottom floor
	  * @param  customerList  Customers who called this elevator
	  */
	public Elevator(int numFloors, int bottomFloor, ArrayList<Customer> customerList) {
		setBottomFloor(bottomFloor);
		setNumFloorsAndTopFloor(numFloors);
		setCurrentFloor(bottomFloor);
		setCustomerList(new ArrayList<Customer>(customerList));
	}
	
	// Getters and Setters START
	/**
	 * Returns the number of floors this elevator serves.
	 * 
	 * @return numFloors
	 */
	public int getNumFloors() {
		return numFloors;
	}
	
	 /**
	  * Takes the number of floors as an argument and sets {@link #numFloors} to its value. It thereafter infers and sets
	  * the {@link #topFloor} value by the values of {@link #numFloors} and {@link #bottomFloor}.
	  * <p>Prints an error message if {@link #numFloors} is smaller than 2.
	 
	  * @param numFloors  Number of floors the elevator serves
	  */
	private void setNumFloorsAndTopFloor(int numFloors) {
		if(numFloors >= 2) {
			this.numFloors = numFloors;
			topFloor = numFloors + bottomFloor - 1;
			if(topFloor >= 13) {
				topFloor++;
			}
		}
		else {
			System.out.println("\nError: The elevator's building must have at least two floors.");
		}
	}
	
	/**
	 * Returns this elevator's register list - the list of customers who are currently in the elevator.
	 * 
	 * @return registerList
	 */
	public ArrayList<Customer> getRegisterList() {
		return registerList;
	}
	
	/**
	 * Returns this elevator's customer list - the people who called the elevator.
	 * 
	 * @return customerList
	 */
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	/**
	 * Sets the customer list - the people who called the elevator.
	 * 
	 * @param customerList  Customers who called the elevator
	 */
	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	/**
	 * Returns this elevator's current floor.
	 * 
	 * @return currentFloor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	/**
	 * Sets this elevator's {@link #currentFloor}.
	 * <p>Prints error message if <code>currentFloor</code> parameter is not between the {@link #bottomFloor} and {@link #topFloor}.  
	 * 
	 * @param currentFloor  This elevator's current floor
	 */
	public void setCurrentFloor(int currentFloor) {
		if(currentFloor >= bottomFloor && currentFloor <= topFloor) {
			this.currentFloor = currentFloor;
		}
		else {
			System.out.println("\nError: illegal floor in method setCurrentFloor.");
		}
	}
	
	/**
	 * Returns this elevator's top floor.
	 * 
	 * @return topFloor
	 */
	public int getTopFloor() {
		return topFloor;
	}
	
	/**
	 * Returns this elevator's bottom floor.
	 * 
	 * @return bottomFloor
	 */
	public int getBottomFloor() {
		return bottomFloor;
	}
	
	/**
	 * Sets this elevator's {@link #bottomFloor}.
	 * 
	 * @param bottomFloor  This elevator's bottom floor.
	 */
	private void setBottomFloor(int bottomFloor) {
		this.bottomFloor = bottomFloor;
	}
	
	/**
	 * Returns this elevator's current direction
	 * 
	 * @return direction
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Sets this elevator's {@link #direction}.
	 * 
	 * @param direction  This elevator's current direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Returns whether or not annotations are turned on (<code>true</code>/<code>false</code>)
	 * 
	 * @return annotations
	 */
	public boolean isAnnotations() {
		return annotations;
	}
	
	/**
	 * Sets whether {@link #annotations} are on or off (<code>true</code>/<code>false</code>)
	 * 
	 * @param annotations <code>true</code> to turn on, <code>false</code> to turn off.
	 */
	public void setAnnotations(boolean annotations) {
		this.annotations = annotations;
	}
	// Getters and Setters END

	/** 
	 * Prints the parameter only if {@link #annotations} are turned on (i.e. <code>true</code>).
	 * 
	 * @param printStr  The String to print
	 */
	private void printlnIfAnnotationsOn(String printStr) {
		if(annotations) {
			System.out.println(printStr);
		}
	}
	
	/**
	 * Iterates through customerList, setting each Customer's "finished" attribute to false
	 * (via the <code>Customer</code>'s <code>setFinished</code> method).
	 */
	public void setAllCustomersToNotFinished() {
		for(Customer customer : customerList) {
			customer.setFinished(false);
		}
	}
	
	/**
	 * Starts this elevator at default strategy:
	 * <ul>
	 * <li>Starts at the bottom floor.</li>
	 * <li>Goes to the top floor.</li>
	 * <li>Goes back down to the bottom floor.</li>
	 * <li>Picks up and drops off customers according to the data in {@link #customerList}, using the methods {@link #pickUpCustomers()} and
	 * {@link #dropOffCustomers()}, respectively.</li>
	 * </ul>
	 * 
	 * Once finished, this method returns the total amount of moves this elevator has done.
	 * 
	 * @return movesCounter
	 */
	public int startElevatorDefaultStrategy() {
		printlnIfAnnotationsOn("\n--Default strategy elevator started--");
		int movesCounter = 0; // Counts how many moves the elevator has done.
		int floorBeforeMove = bottomFloor + 1; // Stores the floor number before moving. Initialised to bottomFloor + 1.
		setCurrentFloor(bottomFloor); // Start the elevator from the bottom floor.
		do {
			// - Print the elevator's location if annotations are turned on.
			printlnIfAnnotationsOn("\nElevator is at floor " + currentFloor);

			// Drop off any customers?
			dropOffCustomers();
			
			// If at top floor, change elevator direction to "down".
			if(currentFloor == topFloor) {
				setDirection("down");
			}
			
			// If this is not the first iteration of this loop (in which the current floor is the bottom floor and the direction is up:
			// store currentFloor's value in floorBeforeMove.
			if(!(currentFloor == bottomFloor && direction.equals("up"))) {
				floorBeforeMove = currentFloor;
			}
			
			// If the elevator has not reached its final stop (i.e. current floor is the bottom floor and direction is down):
			// - Print the elevator's direction if annotations are turned on.
			// - Check if customers need to be picked up.
			// - Print customers in elevator if annotations are turned on.
			// - Move the elevator once in its direction.
			// - Increment movesCounter.
			if(!(currentFloor == bottomFloor && direction.equals("down"))) {
				printlnIfAnnotationsOn("Elevator going " + direction);
					
				// Pick up any customers?
				pickUpCustomers();
				
				printCustomersInElevatorIfAnnotationsOn();
				
				move();
				movesCounter++;
			}
		}
		// Do the above while floorBeforeMove is bigger than the bottomFloor.
		while(floorBeforeMove > bottomFloor); // end while
		
		return movesCounter;
	}
	
	 /**
	  * Starts the elevator at improved strategy:
	  * <ul>
	  * <li>Starts at the <strong>current</strong> floor (This saves the elevator a trip to the bottom floor, if a second run was
	  * needed (and implemented).</li>
	  * <li>Only goes as high as necessary on the way up, and as low as necessary on the way down, using the methods:
	  * {@link #unservedCustomersRemain()} and {@link #customersWaitingInDirection()} to determine whether to
	  * keep moving / change direction / stop.</li>
	  * <li>Picks up and drops off customers according to the data in {@link #customerList}, using the methods {@link #pickUpCustomers()}
	  * and {@link #dropOffCustomers()}, respectively.</li>
	  * </ul>
	  * 
	  * Once finished, this method returns the total amount of moves this elevator has done.
	  * 
	  * @return movesCounter
	  */
	public int startElevatorImprovedStrategy() {
		printlnIfAnnotationsOn("\n--Improved strategy elevator started--"); // Testing only - remove later
		int movesCounter = 0; // Counts how many moves the elevator has done.

		// While the are still customers who have not reached their destination floors
		while(unservedCustomersRemain()) {
			// - Print the elevator's location if annotations are turned on.
			printlnIfAnnotationsOn("\nElevator is at floor " + currentFloor);
			
			// Drop off any customers?
			dropOffCustomers();			
			
			// Set relevant direction at end floors.
			if(currentFloor == topFloor) {
				setDirection("down");
			}
			else {
				if(currentFloor == bottomFloor) {
					setDirection("up");
				}
			}
			
			// If unserved customers remain:
			if(unservedCustomersRemain()) {
				// If no customers are waiting in the elevator's direction and no customers are currently in the lift:
				// - Print the corresponding message if annotations are turned on.
				// - Change the elevator's direction.
				if(!customersWaitingInDirection() && registerList.size() == 0) {
					printlnIfAnnotationsOn("--No more customers waiting in direction and no customers in lift.--");
					changeDirection();
				}
				
				// - Print the elevator's direction if annotations are turned on.
				printlnIfAnnotationsOn("Elevator going " + direction);
				
				// Pick up any customers?
				pickUpCustomers();
				
				// Print customers in elevator if annotations are turned on. 
				printCustomersInElevatorIfAnnotationsOn();
				
				// Move the lift one floor in its direction; Increment move counter.
				move();
				movesCounter++;
			}
			else {
				// Otherwise print appropriate message if annotations are turned on. 
				printlnIfAnnotationsOn("\n--No more customers waiting in building--\n");
			}
		} // end while
		
		return movesCounter;
	}
	
	/**
	 * Iterates through {@link #customerList} and returns <code>true</code> if any customer is
	 * not finished (<code>isFinished == false</code>), and <code>false</code> if all customers are finished.
	 *  
	 * @return  whether or not unserved customers remain
	 */
	private boolean unservedCustomersRemain() {
		for(Customer customer : customerList) {
			if(customer.isFinished() == false) {
				return true;
			}
		}
		return false;
	}
	
	 /**
	  * Checks if any Customers in the elevator need to be dropped off at the current floor.
	  * <p>Iterates through {@link #registerList} and does the following for each customer with the same destination floor as
	  * the current floor:
	  * <ul>
	  * <li>Prints a message saying which customer got dropped off, if {@link #annotations} are turned on.</li>
	  * <li>Invokes the {@link #customerLeaves(Customer)} method with the Customer object sent as an argument.</li>
	  * </ul>
	  */
	private void dropOffCustomers() {
		for(int i=0; i<registerList.size(); i++) {
			if(registerList.get(i).getDestinationFloor() == currentFloor) {
				printlnIfAnnotationsOn("Customer " + registerList.get(i).getId() + " dropped off.");
				customerLeaves(registerList.get(i));
				i--;
			}
		}
	}
	
	 /**
	  * Checks if any Customers need to be picked up at the current floor.
	  * <p>Iterates through {@link #customerList} and looks for Customers who are:
	  * <ul>
	  * <li>not finished, AND</li>
	  * <li>not in this elevator, AND</li>
	  * <li>are on the same floor as this elevator, AND</li>
	  * <li>the Customer wants to go in the same direction as this elevator's.</li>
	  * </ul>
	  * For any such Customer, the method:
	  * <ul>
	  * <li>Prints a message saying which customer got picked up, if {@link #annotations} are turned on.</li>
	  * <li>Calls the {@link #customerJoins(Customer)} method with the Customer object sent as an argument.</li>
	  * </ul>
	  */
	private void pickUpCustomers() {
		for(Customer customer : customerList) {
			if(!customer.isFinished() && !customer.isInElevator() && customer.getCurrentFloor() == currentFloor && customer.calcDirection().equals(direction)) {
				printlnIfAnnotationsOn("Customer " + customer.getId() + " picked up.");
				customerJoins(customer);
			}
		}
	}
	
	 /**
	  * Invoked when it has been determined that a Customer should join the elevator.
	  * <p>Takes a Customer object and does 2 things:
	  * <ul>
	  * <li>Sets the Customer's <code>inElevator</code> property to <code>true</code>.</li>
	  * <li>Adds the Customer to the {@link #registerList}.</li>
	  * </ul>
	  * 
	  * @param customer  The Customer object in question
	  */
	private void customerJoins(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(true);
		registerList.add(customer);
	}
	
	 /**
	  * Invoked when it has been determined that a Customer should leave the elevator.
	  * <p>It takes a Customer object and does 3 things:
	  * <ul>
	  * <li>Sets the Customer's <code>inElevator</code> property to <code>false</code>.</li>
	  * <li>Sets the Customer's <code>isFinished</code> property to <code>true</code>.</li>
	  * <li>Removes the Customer from the {@link #registerList}.</li>
	  * </ul>
	  * 
	  * @param customer  The Customer object in question
	  */
	private void customerLeaves(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(false);
		customerList.get(customerList.indexOf(customer)).setFinished(true);
		registerList.remove(customer);
	}
	
	/**
	 * Returns <code>true</code> if there are Customers waiting in this elevator's {@link #direction}, and
	 * <code>false</code> otherwise.
	 * 
	 * @return whether or not any customers are waiting in this elevator's {@link #direction}.
	 */
	private boolean customersWaitingInDirection() {
		// If elevator is going up, for all Customers, if a Customer is:
		// - not finished, AND
		// - not in the elevator, AND
		// - on a higher floor than the elevator's, OR customer is on the same floor and heading up
		// --> return true.
		if(direction.equals("up")) {
			for(Customer customer : customerList) {
				if(!customer.isFinished() && !customer.isInElevator()) {
					if(customer.getCurrentFloor() > currentFloor ||
							(customer.getCurrentFloor() == currentFloor && customer.calcDirection().equals("up"))) {
						return true;
					}
				}
			}
		}
		else {
			// Otherwise, If elevator is going down, for all Customers, if a Customer is:
			// - not finished, AND
			// - not in the elevator, AND
			// - on a lower floor than the elevator's, OR customer is on the same floor and heading down
			// --> return true.
			if(direction.equals("down")) {
				for(Customer customer : customerList) {
					if(!customer.isFinished() && !customer.isInElevator()) {
						if(customer.getCurrentFloor() < currentFloor ||
								(customer.getCurrentFloor() == currentFloor && customer.calcDirection().equals("down"))) {
							return true;
						}
					}
				}
			}
			else {
				// Otherwise print an error message.
				System.out.println("\n--Error: invalid elevator direction (observed from the method customersWaitingInDirection()--\n");
			}
		}
		// If non of the above returned true, the method returns false.
		return false;
	}
	
	/**
	 * Changes this elevator's {@link #direction} to its opposite if it's "up" or "down", and prints and error otherwise.
	 * <p>Upon changing the direction, if {@link #annotations} are turned on, it prints a message saying what direction it changed it to.
	 */
	private void changeDirection() {
		if(direction == "up") {
			printlnIfAnnotationsOn("--Changing direction to down--");
			setDirection("down");
		}
		else {
			if(direction == "down") {
				printlnIfAnnotationsOn("--Changing direction to up--");
				setDirection("up");
			}
			else {
				System.out.println("\n--Error when trying to change elevator direction: direction is neither up nor down.--");
			}
		}
	}
	
	/** If {@link #annotations} are turned on, iterates through the {@link #registerList} and prints all Customers within it. */
	private void printCustomersInElevatorIfAnnotationsOn() {
		if(annotations) {
			System.out.print("Customers in elevator: ");
			if(registerList.size() > 0) {
				for(int i=0; i<registerList.size(); i++) {
					System.out.print(registerList.get(i).getId());
					if(i < registerList.size() -1) {
						System.out.print(", ");
					}
				}
				System.out.println("");
			}
			else {
				System.out.println(" none");
			}
		}
	}
	
	/** Moves the elevator once in its {@link #direction}. If after the move the elevator is at floor 13,
	 * this method will move the elevator again. */
	private void move() {
		if(direction == "up") {
			currentFloor++;
		}
		else {
			currentFloor--;
		}
		if(currentFloor == 13) {
			move();
		}
	}
	
	/**
	 * Returns a summary of this elevator's properties in a readable format.
	 * 
	 * @return  objString
	 */
	@Override
	public String toString() {
		String objString = "";
		objString += "numFloors: " + numFloors + "\ncurrentFloor: " + currentFloor + "\ntopFloor: " + topFloor
				+ "\nbottomFloor: " + bottomFloor + "\ndirection: " + direction + "\n\n";
		return objString;
	}
}
