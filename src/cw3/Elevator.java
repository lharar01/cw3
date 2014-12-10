package cw3;

import java.util.ArrayList;

public class Elevator {
	private int numFloors = 0;
	// Customers in the elevator
	private ArrayList<Customer> registerList = new ArrayList<Customer>();
	// Customers who called the elevator
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int currentFloor = 0;
	private int topFloor = 0;
	private int bottomFloor = 0;
	private String direction = "up";
	// Turns annotations on or off
	private boolean annotations = false;
	
	// Elevator constructor:
	// - Takes as arguments: number of floors, bottom floor and a Customer list.
	// - Sets the bottomFloor, numFloors, bottomFloor and customerList.
	// - Infers and sets the topFloor.
	public Elevator(int numFloors, int bottomFloor, ArrayList<Customer> customerList) {
		setBottomFloor(bottomFloor);
		setNumFloorsAndTopFloor(numFloors);
		setCurrentFloor(bottomFloor);
		setCustomerList(new ArrayList<Customer>(customerList));
	}
	
	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}
	
	// This method gets the number of floors as an argument and sets numFloors to its value. It thereafter infers the topFloor value by the values of
	// numFloors and bottomFloor and sets the topFloor value.
	// It prints an error message if numFloors is smaller than 2.
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

	public ArrayList<Customer> getRegisterList() {
		return registerList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		if(currentFloor >= bottomFloor && currentFloor <= topFloor) {
			this.currentFloor = currentFloor;
		}
		else {
			System.out.println("\nError: illegal floor in method setCurrentFloor.");
		}
	}
	
	public int getTopFloor() {
		return topFloor;
	}
	
	public int getBottomFloor() {
		return bottomFloor;
	}
	
	private void setBottomFloor(int bottomFloor) {
		this.bottomFloor = bottomFloor;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public boolean isAnnotations() {
		return annotations;
	}

	public void setAnnotations(boolean annotations) {
		this.annotations = annotations;
	}
	// Getters and Setters END

	// This method takes a String as an argument and performs System.out.println with it as an argument, only if annotations are on (i.e. true).
	private void printlnIfAnnotationsOn(String printStr) {
		if(annotations) {
			System.out.println(printStr);
		}
	}
	
	// This method iterates through customerList, setting each one's "finished" attribute to false (via the setFinished() method). 
	public void setAllCustomersToNotFinished() {
		for(Customer customer : customerList) {
			customer.setFinished(false);
		}
	}
	
	// This method starts the elevator at default strategy:
	// - Start at the bottom floor.
	// - Go to the top floor.
	// - Go back down to the bottom floor.
	// - Pick up and drop off customers according to the data in customerList, using the methods pickUpCustomers() and dropOffCustomers(), respectively.
	//
	// Once finished, it then returns the total amount of moves the elevator has done to the calling method.
	public int startElevatorDefaultStrategy() {
		printlnIfAnnotationsOn("\n--Default strategy elevator started--");
		int moveCounter = 0; // Counts how many moves the elevator has done.
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
			// - Increment moveCounter.
			if(!(currentFloor == bottomFloor && direction.equals("down"))) {
				printlnIfAnnotationsOn("Elevator going " + direction);
					
				// Pick up any customers?
				pickUpCustomers();
				
				printCustomersInElevatorIfAnnotationsOn();
				
				move();
				moveCounter++;
			}
		}
		// Do the above while floorBeforeMove is bigger than the bottomFloor.
		while(floorBeforeMove > bottomFloor); // end while
		
		return moveCounter;
	}
	
	// This method starts the elevator at improved strategy:
		// - Start at the CURRENT floor. (This saves the elevator a trip to the bottom floor, if a second run was needed (and implemented).
		// - Only go as high as necessary on the way up, and as low as necessary on the way down, using the methods: unservedCustomersRemain() and
		// customersWaitingInDirection() to determine whether to keep moving / change direction / stop.
		// - Pick up and drop off customers according to the data in customerList, using the methods pickUpCustomers() and dropOffCustomers(), respectively.
		//
		// Once finished, it then returns the total amount of moves the elevator has done to the calling method.
	public int startElevatorImprovedStrategy() {
		printlnIfAnnotationsOn("\n--Improved strategy elevator started--"); // Testing only - remove later
		int moveCounter = 0; // Counts how many moves the elevator has done.

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
				moveCounter++;
			}
			else {
				// Otherwise print appropriate message if annotations are turned on. 
				printlnIfAnnotationsOn("\n--No more customers waiting in building--\n");
			}
		} // end while
		
		return moveCounter;
	}
	
	// This method iterates through customerList and returns true if any customer is not finished (isFinished == false), and false if all customers are finished. 
	private boolean unservedCustomersRemain() {
		for(Customer customer : customerList) {
			if(customer.isFinished() == false) {
				return true;
			}
		}
		return false;
	}
	
	// This method checks if any Customers in the elevator need to be dropped at the current floor.
	// It iterates through registerList and does the following for each customer with the same destination floor as the current floor:
	// - Prints a message saying which customer got dropped off, if annotations are turned on.
	// - Calls the customerLeaves() method (described later) with the Customer object sent as an argument.
	private void dropOffCustomers() {
		for(int i=0; i<registerList.size(); i++) {
			if(registerList.get(i).getDestinationFloor() == currentFloor) {
				printlnIfAnnotationsOn("Customer " + registerList.get(i).getId() + " dropped off.");
				customerLeaves(registerList.get(i));
				i--;
			}
		}
	}
	
	// This method checks if any Customers need to be picked up at the current floor.
	// It iterates through customerList and looks for Customers who are: 
	// - not finished, AND
	// - not in elevator, AND
	// - are on the same floor as the elevator, AND
	// - if the Customer wants to go in the same direction as the elevator's.
	//
	// For any such Customer, the method:
	// - Prints a message saying which customer got picked up, if annotations are turned on.
	// - Calls the customerJoins() method (described later) with the Customer object sent as an argument. 
	private void pickUpCustomers() {
		for(Customer customer : customerList) {
			if(!customer.isFinished() && !customer.isInElevator() && customer.getCurrentFloor() == currentFloor && customer.calcDirection().equals(direction)) {
				printlnIfAnnotationsOn("Customer " + customer.getId() + " picked up.");
				customerJoins(customer);
			}
		}
	}
	
	// This method is called when it has been determined that a Customer should join the elevator.
	// It takes a Customer object and does 2 things:
	// - Sets the Customer's inElevator property to true.
	// - Adds the Customer to the registerList.
	private void customerJoins(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(true);
		registerList.add(customer);
	}
	
	// This method is called when it has been determined that a Customer should leave the elevator.
	// It takes a Customer object and does 3 things:
	// - Sets the Customer's inElevator property to false.
	// - Sets the Customer's isFinished property to true.
	// - Removes the Customer from the registerList.
	private void customerLeaves(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(false);
		customerList.get(customerList.indexOf(customer)).setFinished(true);
		registerList.remove(customer);
	}
	
	// This method returns true if there are Customers waiting in the elevator's direction, and false otherwise.
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
	
	// This method changes the elevator's direction to its opposite if it's "up" or "down", and prints and error otherwise.
	// Upon changing the direction, if annotations are turned on, it prints a message saying what direction it changed it to.
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
	
	// If annotations are turned on, this method iterates through the registerList and prints all Customers within it.
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
	
	// This method moves the elevator once in its direction. If after the move the elevator is at floor 13, the method will move the elevator again.
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
	
	@Override
	public String toString() {
		String objString = "";
//		objString += "numFloors: " + numFloors + "\nregisterList:\n";
//		for(int i=0; i<registerList.size(); i++) {
//			objString += registerList.get(i);
//		}
		objString += "numFloors: " + numFloors + "\ncurrentFloor: " + currentFloor + "\ntopFloor: " + topFloor
				+ "\nbottomFloor: " + bottomFloor + "\ndirection: " + direction + "\n\n";
		return objString;
	}
}
