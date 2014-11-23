package cw3;

import java.util.ArrayList;

public class Elevator {
	private int numFloors = 0;
	private ArrayList<Customer> registerList = new ArrayList<Customer>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int currentFloor = 0;
	private int topFloor = 0;
	private int bottomFloor = 0;
	private String direction = "up";
	private boolean annotations = false;
	
	public Elevator(int numFloors, int bottomFloor, ArrayList<Customer> customerList) {
		setBottomFloor(bottomFloor);
		setNumFloors(numFloors);
		setCurrentFloor(bottomFloor);
		setCustomerList(new ArrayList<Customer>(customerList));
	}
	
	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	private void setNumFloors(int numFloors) {
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

//	public void setRegisterList(ArrayList<Customer> registerList) {
//		this.registerList = registerList;
//	}

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
		this.currentFloor = currentFloor;
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
	// Getters and Setters END
	
	public boolean isAnnotations() {
		return annotations;
	}

	public void setAnnotations(boolean annotations) {
		this.annotations = annotations;
	}

	private void printlnIfAnnotationsOn(String printStr) {
		if(annotations) {
			System.out.println(printStr);
		}
	}
	
	public void setAllCustomersToNotFinished() {
		for(Customer customer : customerList) {
			customer.setFinished(false);
		}
	}
	
	public int startElevatorDefaultStrategy() {
		printlnIfAnnotationsOn("\n--Default strategy elevator started--"); // Testing only - remove later
		int moveCounter = 0;
		int floorBeforeMove = bottomFloor + 1;
		setCurrentFloor(bottomFloor);
		do {
			printlnIfAnnotationsOn("\nElevator is at floor " + currentFloor);
			if(customerList.size() > 0 || registerList.size() > 0) {
				// Drop off any customers?
				dropOffCustomers();
			}
			
			// If at top floor, change elevator direction to "down".
			if(currentFloor == topFloor) {
				setDirection("down");
			}
			
			if(!(currentFloor == bottomFloor && direction == "up")) {
				floorBeforeMove = currentFloor;
			}
			
			if(!(currentFloor == bottomFloor && direction == "down")) {
				// Testing only - remove later
				printlnIfAnnotationsOn("Elevator going " + direction);
					
				if(customerList.size() > 0 || registerList.size() > 0) {
					// Pick up any customers?
					pickUpCustomers();
				}
				
				// Testing only - remove later
				printCustomersInElevatorIfAnnotationsOn();
				
				move();
				moveCounter++;
			}
		}
		while(floorBeforeMove > bottomFloor);
		return moveCounter;
	}
	
	public int startElevatorImprovedStrategy() {
		printlnIfAnnotationsOn("\n--Improved strategy elevator started--"); // Testing only - remove later
		int moveCounter = 0;
		//ArrayList<Customer> currentCustomerList = new ArrayList<Customer>(customerList);
		//int oldNumberOfCustomers = customerList.size(); // Remove later
		//while(currentCustomerList.size() > 0 || registerList.size() > 0) {
		// THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		while(unservedCustomersRemain()) {
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
			
			//unservedCustsRemain = unservedCustomersRemain();
			// If no customers waiting in direction and register list empty, change direction. If no customers waiting in new direction, print "no customers in
			// building..." and break.
			if(unservedCustomersRemain()) {
				if(!customersWaitingInDirection() && registerList.size() == 0) {
					printlnIfAnnotationsOn("--No more customers waiting in direction and no customers in lift.--");
					//if(currentFloor != bottomFloor && currentFloor != topFloor) {
					//if(unservedCustomersRemain()) {
					changeDirection();
				}
					// Testing only - remove later
				printlnIfAnnotationsOn("Elevator going " + direction);
				
				// Pick up any customers?
				pickUpCustomers();
				
				// Testing only - remove later
				printCustomersInElevatorIfAnnotationsOn();
				
				//if(customersWaitingInDirection() || registerList.size() > 0) {
				move();
				moveCounter++;
			}
			else {
				printlnIfAnnotationsOn("\n--No more customers waiting in building--\n");
				//break;
			}
		} // end while
		//printlnIfAnnotationsOn("\nRepopulating Customers for testing."); // testing
		//populateCustomerList(oldNumberOfCustomers); // testing
		return moveCounter;
	}
	
//	private void removeCustomer(Customer customer) {
//		//customer.setInElevator(false);
//		customerList.remove(customer);
//	}
	
	private boolean unservedCustomersRemain() {
		for(Customer customer : customerList) {
			if(customer.isFinished() == false) {
				return true;
			}
		}
		return false;
	}
	
	private void dropOffCustomers() {
		for(int i=0; i<registerList.size(); i++) {
			if(registerList.get(i).getDestinationFloor() == currentFloor) {
				printlnIfAnnotationsOn("Customer " + registerList.get(i).getId() + " dropped off."); // Testing only - remove later
				customerLeaves(registerList.get(i));
				i--;
			}
		}
	}
	
	private void pickUpCustomers() {
		//for(int i=0; i<customerList.size(); i++) {
		for(Customer customer : customerList) {
			if(!customer.isFinished() && !customer.isInElevator()) {
				if(customer.getCurrentFloor() == currentFloor && customer.calcDirection().equals(direction) ) {
					printlnIfAnnotationsOn("Customer " + customer.getId() + " picked up."); // Testing only - remove later
					customerJoins(customer);
					//removeCustomer(customer);				
					//i--;
				}
			}
		}
	}
	
	private void customerJoins(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(true);
		registerList.add(customer);
	}
	
	private void customerLeaves(Customer customer) {
//		registerList.get(i).setInElevator(false);
//		registerList.get(i).setFinished(true);
		customerList.get(customerList.indexOf(customer)).setInElevator(false);
		customerList.get(customerList.indexOf(customer)).setFinished(true);
		registerList.remove(customer);
	}
	
	private boolean customersWaitingInDirection() {
		// If elevator is going up, for all customers, if customer is on a higher floor, or customer is on the same floor and heading up - return true.
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
			// If elevator is going down, for all customers, if customer is on a lower floor, or customer is on the same floor and heading down - return true.
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
				System.out.println("\n--Invalid elevator direction--\n");
			}
		}
		return false;
	}
	
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
				System.out.println("Error when trying to change elevator direction: direction is neither up nor down.");
			}
		}
	}
	
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
