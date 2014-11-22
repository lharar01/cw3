package cw3;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors = 0;
	private int bottomFloor = 0;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private Elevator elevator = null;
	
	public Building(int numFloors, int numCustomers, int bottomFloor) {
		setBottomFloor(bottomFloor);
		setNumFloors(numFloors);
		populateCustomerList(numCustomers);
		setElevator(new Elevator(numFloors, bottomFloor));
	}
	
	private void populateCustomerList(int numCustomers) {
		if(numCustomers > 0 && numFloors >= 2) {
			int numFloorsForRand = numFloors;
			if(numFloors >= 14) {
				numFloorsForRand++;
			}
			Random rand = new Random();
			for(int i=0; i<numCustomers; i++) {
				int rand1, rand2;
				rand1 = rand.nextInt(numFloorsForRand) + bottomFloor;
				if(rand1 == 13) {
					rand1++;
				}
				do {
					rand2 = rand.nextInt(numFloorsForRand) + bottomFloor;
					if(rand2 == 13) {
						rand2++;
					}
				}
				while(rand1 == rand2);
				customerList.add(new Customer(rand1, rand2));
			}
		}
		else {
			if(numCustomers <= 0)
			{
				System.out.println("\nError: The building must have at least one customer and 2 floors.");
			}
		}
	}
	
	public int getBottomFloor() {
		return bottomFloor;
	}

	private void setBottomFloor(int bottomFloor) {
		this.bottomFloor = bottomFloor;
	}

	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	private void setNumFloors(int numFloors) {
		if(numFloors >= 2) {
			this.numFloors = numFloors;
		}
		else {
			System.out.println("\nError: The building must have at least two floors.");
		}
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	/*public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}*/

	public Elevator getElevator() {
		return elevator;
	}

	private void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	// Getters and Setters END
	
	private void removeCustomer(Customer customer) {
		//customer.setInElevator(false);
		customerList.remove(customer);
	}
	
	public void startElevatorDefaultStrategy() {
		System.out.println("Default strategy elevator started."); // Testing only - remove later
		elevator.setCurrentFloor(bottomFloor);
		//while(customerList.size() > 0) { THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		do {
			System.out.println("\nElevator is at floor " + elevator.getCurrentFloor());
			if(customerList.size() > 0 || elevator.getRegisterList().size() > 0) {
				// Drop off any customers?
				dropOffCustomers();
			}
			
			// If at top floor, change elevator direction to "down".
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			
			// Testing only - remove later
			System.out.println("Elevator going " + elevator.getDirection());
				
			if(customerList.size() > 0 || elevator.getRegisterList().size() > 0) {
				// Pick up any customers?
				pickUpCustomers();
			}
			
			// Testing only - remove later
			printCustomersInElevator();
			
			elevator.move();
		}
		while(elevator.getCurrentFloor() >= bottomFloor);
	}
	
	public void startElevatorImprovedStrategy() {
		System.out.println("Improved strategy elevator started."); // Testing only - remove later
		int oldNumberOfCustomers = customerList.size(); // Remove later
		while(customerList.size() > 0 || elevator.getRegisterList().size() > 0) {
			System.out.println("\nElevator is at floor " + elevator.getCurrentFloor());
			
			// Drop off any customers?
			dropOffCustomers();			
			
			// Set relevant direction at end floors.
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			else {
				if(elevator.getCurrentFloor() == elevator.getBottomFloor()) {
					elevator.setDirection("up");
				}
			}
			
			// If no customers waiting in direction and register list empty, change direction. If no customers waiting in new direction, print "no customers in
			// building..." and break.
			if(!customersWaitingInDirection() && elevator.getRegisterList().size() == 0) {
				System.out.println("--No more customers waiting in direction and no customers in lift.--");
				if(elevator.getCurrentFloor() != elevator.getBottomFloor() && elevator.getCurrentFloor() != elevator.getTopFloor()) {
					elevator.changeDirection();
				}
				if(!customersWaitingInDirection()) {
					System.out.println("\n--No more customers waiting in building--\n");
					break;
				}
			}
			
			// Testing only - remove later
			System.out.println("Elevator going " + elevator.getDirection());
			
			// Pick up any customers?
			pickUpCustomers();
			
			// Testing only - remove later
			printCustomersInElevator();
			
			if(customersWaitingInDirection() || elevator.getRegisterList().size() > 0) {
				elevator.move();
			}
		} // end while
		System.out.println("\nRepopulating Customers for testing."); // testing
		populateCustomerList(oldNumberOfCustomers); // testing
	}
	
	private void dropOffCustomers() {
		for(int i=0; i<elevator.getRegisterList().size(); i++) {
			if(elevator.getRegisterList().get(i).getDestinationFloor() == elevator.getCurrentFloor()) {
				System.out.println("Customer " + elevator.getRegisterList().get(i).getId() + " dropped off."); // Testing only - remove later
				elevator.customerLeaves(elevator.getRegisterList().get(i));
				i--;
			}
		}
	}
	
	private void pickUpCustomers() {
		for(int i=0; i<customerList.size(); i++) { 
			if(customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection().equals(elevator.getDirection()) ) {
				System.out.println("Customer " + customerList.get(i).getId() + " picked up."); // Testing only - remove later
				elevator.customerJoins(customerList.get(i));
				removeCustomer(customerList.get(i));
				i--;
			}
		}
	}
	
	private void printCustomersInElevator() {
		System.out.print("Customers in elevator: ");
		if(elevator.getRegisterList().size() > 0) {
			for(int i=0; i<elevator.getRegisterList().size(); i++) {
				System.out.print(elevator.getRegisterList().get(i).getId());
				if(i < elevator.getRegisterList().size() -1) {
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
		if(elevator.getDirection().equals("up")) {
			for(Customer customer : customerList) {
				if(customer.getCurrentFloor() > elevator.getCurrentFloor() || (customer.getCurrentFloor() == elevator.getCurrentFloor() && customer.calcDirection().equals("up"))) {
					return true;
				}
			}
		}
		else {
			// If elevator is going down, for all customers, if customer is on a lower floor, or customer is on the same floor and heading down - return true.
			if(elevator.getDirection().equals("down")) {
				for(Customer customer : customerList) {
					if(customer.getCurrentFloor() < elevator.getCurrentFloor() || (customer.getCurrentFloor() == elevator.getCurrentFloor() && customer.calcDirection().equals("down"))) {
						return true;
					}
				}
			}
			else {
				System.out.println("\n--Invalid elevator direction--\n");
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String objString = "";
		objString += "numFloors: " + numFloors + "\nbottomFloor: " + bottomFloor + "\n\ncustomerList:\n";
		for(int i=0; i<customerList.size(); i++) {
			objString += "\n" + (i+1) + ".\n" + customerList.get(i);
		}
		objString += "Elevator:\n\n" + elevator;
		return objString;
	}
}