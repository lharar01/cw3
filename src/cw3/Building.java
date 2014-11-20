package cw3;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors = 0;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private Elevator elevator = null;
	
	public Building(int numFloors, int numCustomers) {
		setNumFloors(numFloors);
		populateCustomerAL(numCustomers);
		setElevator(new Elevator(numFloors));
	}
	
	private void populateCustomerAL(int numCustomers) {
		if(numCustomers > 0 && numFloors >= 2) {
			int numFloorsForRand = numFloors;
			if(numFloors >= 14) {
				numFloorsForRand++;
			}
			Random rand = new Random();
			for(int i=0; i<numCustomers; i++) {
				int rand1, rand2;
				rand1 = rand.nextInt(numFloorsForRand);
				if(rand1 == 13) {
					rand1++;
				}
				do {
					rand2 = rand.nextInt(numFloorsForRand);
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
				System.out.println("\nError: The building must have at least one customer.");
			}
		}
	}
	
	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
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

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public Elevator getElevator() {
		return elevator;
	}

	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	// Getters and Setters END
	
	private void removeCustomer(Customer customer) {
		customer.setInElevator(false);
		customerList.remove(customer);
	}
	
	public void startElevatorDefaultStrategy() {
		System.out.println("Default strategy elevator started."); // Testing only - remove later
		elevator.setCurrentFloor(0);
		//while(customerList.size() > 0) { THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		do {
			// If at top floor, change elevator direction to "down".
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			
			 // Testing only - remove later - START
			System.out.print("\nElevator is at floor " + elevator.getCurrentFloor());
			if(elevator.getCurrentFloor() == elevator.getBottomFloor() && elevator.getDirection() == "down") {
				System.out.println(".");
			}
			else {
				System.out.println(", going " + elevator.getDirection() + ".");
			}
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
				System.out.println(" none.");
			}
			 // Testing only - remove later - END
			
			if(customerList.size() > 0 || elevator.getRegisterList().size() > 0) { // Only needed for default strategy.

				// Drop off any customers?
				//ArrayList<Customer> regList = new ArrayList<Customer>(elevator.getRegisterList());
				//int regListLength = regList.size();
				for(int i=0; i<elevator.getRegisterList().size(); i++) {
					if(elevator.getRegisterList().get(i).getDestinationFloor() == elevator.getCurrentFloor()) {
						System.out.println("Customer " + elevator.getRegisterList().get(i).getId() + " dropped off."); // Testing only - remove later
						elevator.customerLeaves(elevator.getRegisterList().get(i));
						i--;
					}
				}
				
				// Pick up any customers?
				//int customerListLength = customerList.size();
				for(int i=0; i<customerList.size(); i++) { 
					if(customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection().equals(elevator.getDirection()) ) {
						System.out.println("Customer " + customerList.get(i).getId() + " picked up."); // Testing only - remove later
						elevator.customerJoins(customerList.get(i));
						removeCustomer(customerList.get(i));
						i--;
					}
				}
			}
			elevator.move();
		}
		while(elevator.getCurrentFloor() >= 0);
	}
	
	public void startElevatorImprovedStrategy() {
		System.out.println("Improved strategy elevator started."); // Testing only - remove later
		int oldNumberOfCustomers = customerList.size(); // Remove later
		// THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		while(customerList.size() > 0 || elevator.getRegisterList().size() > 0) {
			/*if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}*/
			System.out.println("\nElevator is at floor " + elevator.getCurrentFloor());
			// Drop off any customers?
			for(int i=0; i<elevator.getRegisterList().size(); i++) {
				if(elevator.getRegisterList().get(i).getDestinationFloor() == elevator.getCurrentFloor()) {
					System.out.println("Customer " + elevator.getRegisterList().get(i).getId() + " dropped off."); // Testing only - remove later
					elevator.customerLeaves(elevator.getRegisterList().get(i));
					i--;
				}
			}
			
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			else {
				if(elevator.getCurrentFloor() == elevator.getBottomFloor()) {
					elevator.setDirection("up");
				}
			}
			
			// If no customers waiting in direction and register list empty, change direction.
			if(!customersWaitingInDirection() && elevator.getRegisterList().size() == 0) {
				System.out.println("--No more customers waiting in direction and no customers in lift.--");
				if(elevator.getCurrentFloor() != elevator.getBottomFloor() && elevator.getCurrentFloor() != elevator.getTopFloor()) {
					changeDirection();
				}
				if(!customersWaitingInDirection()) {
					System.out.println("\n--No more customers waiting in building--\n");
					break;
				}
			}
			
			// Testing only - remove later - START
			/*if(elevator.getCurrentFloor() == elevator.getBottomFloor() && elevator.getDirection().equals("down")) {
				System.out.println(".");
			}
			else {*/
			System.out.println("Elevator going " + elevator.getDirection());
			//}
			// Testing only - remove later - END
			
			// Pick up any customers?
			for(int i=0; i<customerList.size(); i++) { 
				if(customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection().equals(elevator.getDirection()) ) {
					//if(elevator.getRegisterList().size() < elevator.getMaxPersons()) {
						System.out.println("Customer " + customerList.get(i).getId() + " picked up."); // Testing only - remove later
						elevator.customerJoins(customerList.get(i));
						removeCustomer(customerList.get(i));
						i--;
					//}
//					else {
//						System.out.println("Elevator full. Customer " + customerList.get(i).getId() + " will be picked up on next run.");
//					}
				}
			}
			
			// Testing only - remove later - START
			System.out.print("Customers in elevator (after drop-off and pickup): ");
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
				System.out.println(" none.");
			}
			// Testing only - remove later - END
			
			if(customersWaitingInDirection() || elevator.getRegisterList().size() > 0) {
				elevator.move();
			}
		} // end while
		System.out.println("\nRepopulating Customers for testing."); // testing
		populateCustomerAL(oldNumberOfCustomers); // testing
	}
	
	private boolean customersWaitingInDirection() {
		/*if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
			System.out.println("--Top floor reached--");
			return false;
		}*/
		//if(inclCurrentFloor) {
		// If elevator is going up, for all customers, if customer is on a higher floor, or customer is on the same floor and heading up.
		if(elevator.getDirection().equals("up")) {
			for(int i=0; i<customerList.size(); i++) {
				if(customerList.get(i).getCurrentFloor() > elevator.getCurrentFloor() || (customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection().equals("up"))) {
					return true;
				}
			}
		}
		else {
			// If elevator is going down, for all customers, if customer is on a lower floor, or customer is on the same floor and heading down.
			if(elevator.getDirection().equals("down")) {
				for(int i=0; i<customerList.size(); i++) {
					if(customerList.get(i).getCurrentFloor() < elevator.getCurrentFloor() || (customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection().equals("down"))) {
						return true;
					}
				}
			}
			else {
				System.out.println("\n--Invalid elevator direction--\n");
			}
		}
		
		
		
		
		for(int i=0; i<customerList.size(); i++) {
			if( (elevator.getDirection().equals("up") && customerList.get(i).getCurrentFloor() >= elevator.getCurrentFloor()) || (elevator.getDirection().equals("down") && customerList.get(i).getCurrentFloor() <= elevator.getCurrentFloor()) ) {
				return true;
			}
		}
		//}
//		else {
//			for(int i=0; i<customerList.size(); i++) {
//				if( (elevator.getDirection().equals("up") && customerList.get(i).getCurrentFloor() > elevator.getCurrentFloor()) || (elevator.getDirection().equals("down") && customerList.get(i).getCurrentFloor() < elevator.getCurrentFloor()) ) {
//					return true;
//				}
//			}
//		}
		//System.out.println("\n--No more customers waiting in direction--\n");
		return false;
	}
	
	private void changeDirection() {
		if(elevator.getDirection() == "up") {
			System.out.println("--Changing direction to down--");
			elevator.setDirection("down");
		}
		else {
			if(elevator.getDirection() == "down") {
				System.out.println("--Changing direction to up--");
				elevator.setDirection("up");
			}
			else {
				System.out.println("Error when trying to change elevator direction: direction is neither up nor down.");
			}
		}
	}
	
	@Override
	public String toString() {
		String objString = "";
		objString += "numFloors: " + numFloors + "\n\ncustomerList:\n";
		for(int i=0; i<customerList.size(); i++) {
			objString += "\n" + (i+1) + ".\n" + customerList.get(i);
		}
		objString += "Elevator:\n\n" + elevator;
		return objString;
	}
}