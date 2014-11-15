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
				do {
					rand2 = rand.nextInt(numFloorsForRand);
				}
				while(rand1 == rand2);
				if(rand1 == 13) {
					rand1++;
				}
				else {
					if(rand2 == 13) {
						rand2++;
					}
				}
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
	
	public void startElevator() {
		System.out.println("Elevator started."); // Testing only - remove later
		//while(customerList.size() > 0) { THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		do {
			// If at top floor, change elevator direction to "down".
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			
			System.out.println("\nElevator is at floor " + elevator.getCurrentFloor() + ", going " + elevator.getDirection()); // Testing only - remove later
			
			if(customerList.size() > 0) { // Only needed for default strategy.
				// Drop off any customers?
				ArrayList<Customer> regList = elevator.getRegisterList();
				//int regListLength = regList.size();
				for(int i=0; i<regList.size(); i++) { // if i is not static, store it in a new variable beforehand and use that.
					if(regList.get(i).getDestinationFloor() == elevator.getCurrentFloor()) {
						elevator.customerLeaves(regList.get(i));
						System.out.println("Customer dropped off at floor " + elevator.getCurrentFloor()); // Testing only - remove later
						removeCustomer(customerList.get(i));
						i--;
					}
				}
				
				// Pick up any customers?
				int customerListLength = customerList.size();
				for(int i=0; i<customerListLength; i++) { 
					if(customerList.get(i).getCurrentFloor() == elevator.getCurrentFloor() && customerList.get(i).calcDirection() == elevator.getDirection()) {
						elevator.customerJoins(customerList.get(i));
						System.out.println("Customer picked up at floor " + elevator.getCurrentFloor()); // Testing only - remove later
					}
				}
			}
			elevator.move();
		}
		while(elevator.getCurrentFloor() > 0);
		System.out.println("Elevator is at floor 0"); // Testing only - remove later
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