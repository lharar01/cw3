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
	
	public void startElevatorDefaultStrategy() {
		System.out.println("Default strategy elevator started."); // Testing only - remove later
		ArrayList<Customer> currentCustomerList = new ArrayList<Customer>(customerList); // I need to change everything to keep the customers,
		// so that I can compare the two methods...
		setCurrentFloor(bottomFloor);
		//while(customerList.size() > 0) { THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		do {
			System.out.println("\nElevator is at floor " + getCurrentFloor());
			if(currentCustomerList.size() > 0 || registerList.size() > 0) {
				// Drop off any customers?
				dropOffCustomers();
			}
			
			// If at top floor, change elevator direction to "down".
			if(getCurrentFloor() == getTopFloor()) {
				setDirection("down");
			}
			
			// Testing only - remove later
			System.out.println("Elevator going " + getDirection());
				
			if(currentCustomerList.size() > 0 || registerList.size() > 0) {
				// Pick up any customers?
				pickUpCustomers();
			}
			
			// Testing only - remove later
			printCustomersInElevator();
			
			move();
		}
		while(getCurrentFloor() >= bottomFloor);
	}
	
	public void startElevatorImprovedStrategy() {
		System.out.println("Improved strategy elevator started."); // Testing only - remove later
		ArrayList<Customer> currentCustomerList = new ArrayList<Customer>(customerList);
		//int oldNumberOfCustomers = customerList.size(); // Remove later
		while(currentCustomerList.size() > 0 || registerList.size() > 0) {
			System.out.println("\nElevator is at floor " + getCurrentFloor());
			
			// Drop off any customers?
			dropOffCustomers();			
			
			// Set relevant direction at end floors.
			if(getCurrentFloor() == getTopFloor()) {
				setDirection("down");
			}
			else {
				if(getCurrentFloor() == getBottomFloor()) {
					setDirection("up");
				}
			}
			
			// If no customers waiting in direction and register list empty, change direction. If no customers waiting in new direction, print "no customers in
			// building..." and break.
			if(!customersWaitingInDirection() && registerList.size() == 0) {
				System.out.println("--No more customers waiting in direction and no customers in lift.--");
				if(getCurrentFloor() != getBottomFloor() && getCurrentFloor() != getTopFloor()) {
					changeDirection();
				}
				if(!customersWaitingInDirection()) {
					System.out.println("\n--No more customers waiting in building--\n");
					break;
				}
			}
			
			// Testing only - remove later
			System.out.println("Elevator going " + getDirection());
			
			// Pick up any customers?
			pickUpCustomers();
			
			// Testing only - remove later
			printCustomersInElevator();
			
			if(customersWaitingInDirection() || registerList.size() > 0) {
				move();
			}
		} // end while
		System.out.println("\nRepopulating Customers for testing."); // testing
		//populateCustomerList(oldNumberOfCustomers); // testing
	}
	
//	private void removeCustomer(Customer customer) {
//		//customer.setInElevator(false);
//		customerList.remove(customer);
//	}
	
	private void dropOffCustomers() {
		for(int i=0; i<registerList.size(); i++) {
			if(registerList.get(i).getDestinationFloor() == getCurrentFloor()) {
				System.out.println("Customer " + registerList.get(i).getId() + " dropped off."); // Testing only - remove later
				customerLeaves(registerList.get(i));
				i--;
			}
		}
	}
	
	private void pickUpCustomers() {
		//for(int i=0; i<customerList.size(); i++) {
		for(Customer customer : customerList) {
			if(!customer.isFinished() && !customer.isInElevator()) {
				if(customer.getCurrentFloor() == getCurrentFloor() && customer.calcDirection().equals(getDirection()) ) {
					System.out.println("Customer " + customer.getId() + " picked up."); // Testing only - remove later
					customerJoins(customer);
					//removeCustomer(customer);				
					//i--;
				}
			}
		}
	}
	
	private void printCustomersInElevator() {
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
	
	private boolean customersWaitingInDirection() {
		// If elevator is going up, for all customers, if customer is on a higher floor, or customer is on the same floor and heading up - return true.
		if(getDirection().equals("up")) {
			for(Customer customer : customerList) {
				if(!customer.isFinished() && !customer.isInElevator()) {
					if(customer.getCurrentFloor() > getCurrentFloor() || (customer.getCurrentFloor() == getCurrentFloor() && customer.calcDirection().equals("up"))) {
						return true;
					}
				}
			}
		}
		else {
			// If elevator is going down, for all customers, if customer is on a lower floor, or customer is on the same floor and heading down - return true.
			if(getDirection().equals("down")) {
				for(Customer customer : customerList) {
					if(!customer.isFinished() && !customer.isInElevator()) {
						if(customer.getCurrentFloor() < getCurrentFloor() || (customer.getCurrentFloor() == getCurrentFloor() && customer.calcDirection().equals("down"))) {
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
	
	public void move() {
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
	
	public void customerJoins(Customer customer) {
		customerList.get(customerList.indexOf(customer)).setInElevator(true);
		registerList.add(customer);
	}
	
	public void customerLeaves(Customer customer) {
//		registerList.get(i).setInElevator(false);
//		registerList.get(i).setFinished(true);
		customerList.get(customerList.indexOf(customer)).setInElevator(false);
		customerList.get(customerList.indexOf(customer)).setFinished(true);
		registerList.remove(customer);
	}
	
	public void changeDirection() {
		if(getDirection() == "up") {
			System.out.println("--Changing direction to down--");
			setDirection("down");
		}
		else {
			if(getDirection() == "down") {
				System.out.println("--Changing direction to up--");
				setDirection("up");
			}
			else {
				System.out.println("Error when trying to change elevator direction: direction is neither up nor down.");
			}
		}
	}
	
	@Override
	public String toString() {
		String objString = "";
//		objString += "numFloors: " + numFloors + "\nregisterList:\n";
//		for(int i=0; i<registerList.size(); i++) {
//			objString += registerList.get(i);
//		}
		objString += "numFloors: " + numFloors + "\ncurrentFloor: " + currentFloor + "\ntopFloor: " + topFloor + "\nbottomFloor: " + bottomFloor + "\ndirection: " + direction + "\n\n";
		return objString;
	}
}
