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
	
	public void startElevator() {
		System.out.println("Elevator started."); // Testing only - remove later
		//while(customerList.size() > 0) { THIS IS THE BEGINNING OF THE BETTER STRATERGY. It will make the elevator stop when all customers have been served.
		do {
			// If at top floor, change elevator direction to "down".
			if(elevator.getCurrentFloor() == elevator.getTopFloor()) {
				elevator.setDirection("down");
			}
			
			 // Testing only - remove later - START
			System.out.print("\nElevator is at floor " + elevator.getCurrentFloor());
			if(elevator.getCurrentFloor() == 0 && elevator.getDirection() == "down") {
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