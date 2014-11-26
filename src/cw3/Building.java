package cw3;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors = 0;
	private int bottomFloor = 0;
	private ArrayList<Customer> customerList;
	private Elevator elevator = null;
	
	// Building constructor:
	// - Takes as arguments: number of floors, number of customers and bottom floor.
	// - Sets the class' numFloors and bottomFloor.
	// - Populates customerList with random customers according to the number of customers.
	// - Set the elevator to a new elevator with the arguments: numFloors, bottomFloor and customerList.
	public Building(int numFloors, int numCustomers, int bottomFloor) {
		setBottomFloor(bottomFloor);
		setNumFloors(numFloors);
		populateCustomerList(numCustomers);
		setElevator(new Elevator(numFloors, bottomFloor, customerList));
	}
	
	// This method takes the number of customers as an argument and populates customerList with Customer objects with random currentFloor and destinationFloor.
	// This method ensures that:
	// - the Customers don't have the same value for currentFloor and destinationFloor.
	// - the currentFloor and destinationFloor is not 13 for any given Customer. If a randomly selected number happens to be 13, it is changed to 14.
	public void populateCustomerList(int numCustomers) {
		customerList = new ArrayList<Customer>();
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

	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	// Prints error message if numFloors is smaller than 2.
	private void setNumFloors(int numFloors) {
		if(numFloors >= 2) {
			this.numFloors = numFloors;
		}
		else {
			System.out.println("\nError: The building must have at least two floors.");
		}
	}
	
	public int getBottomFloor() {
		return bottomFloor;
	}

	private void setBottomFloor(int bottomFloor) {
		this.bottomFloor = bottomFloor;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public Elevator getElevator() {
		return elevator;
	}

	private void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	// Getters and Setters END
	
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