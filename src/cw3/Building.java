package cw3;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class denotes an American-style building (no 13th floor) with an elevator and randomly chosen customers.
 * <p>It is used by the AppDriver class to simulate elevator operation.
 * 
 * @author Liran Harary &amp; Shay Meshulam
 * @version 1.0
 * @since 10th November 2014
 */
public class Building {
	/** Number of floors in the building */
	private int numFloors = 0;
	
	/** The building's bottom floor */
	private int bottomFloor = 0;
	
	/** The list of customers waiting for the elevator in the building */
	private ArrayList<Customer> customerList;
	
	/** The building's elevator */
	private Elevator elevator = null;
	
	 /**
	  * <ul>
	  * <li>Takes as arguments: number of floors, number of customers and bottom floor.</li>
	  * <li>Sets this building's {@link #numFloors} and {@link #bottomFloor}.</li>
	  * <li>Populates {@link #customerList} with random customers according to the number of customers.</li>
	  * <li>Set the {@link #elevator} to a new elevator with the arguments: {@link #numFloors}, {@link #bottomFloor} and {@link #customerList}.</li>
	  * </ul>
	  * 
	  * @param  numFloors      Number of floors in this building
	  * @param  numCustomers   Number of customers wishing to use the elevator
	  * @param  bottomFloor    The level of the bottom floor of this building
	  */
	public Building(int numFloors, int numCustomers, int bottomFloor) {
		setBottomFloor(bottomFloor);
		setNumFloors(numFloors);
		populateCustomerList(numCustomers);
		setElevator(new Elevator(numFloors, bottomFloor, customerList));
	}
	
	 /**Takes the number of customers as an argument and populates {@link #customerList} with Customer objects with random
	  * <code>currentFloor</code> and <code>destinationFloor</code>.
	  * <p>Ensures that:
	  * <ul>
	  * <li>the Customers don't have the same value for <code>currentFloor</code> and <code>destinationFloor</code>.</li>
	  * <li>the <code>currentFloor</code> and <code>destinationFloor</code> is not 13 for any given Customer. If a randomly selected number happens to be 13,
	  * it is changed to 14.</li>
	  * </ul>
	  * 
	  * @param  numCustomers  number of random customers to create in {@link #customerList} 
	  */
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
				System.out.println("\nError: The building must have at least one customer.");
			}
		}
	}

	// Getters and Setters START
	/**
	 * Returns the number of floors in this building
	 * 
	 * @return  numFloors
	 */
	public int getNumFloors() {
		return numFloors;
	}

	/** Sets this building's {@link #numFloors}
	 * <p>Prints error message if numFloors is smaller than 2
	 * 
	 * @param  numFloors  Number of floors in this building
	 */
	private void setNumFloors(int numFloors) {
		if(numFloors >= 2) {
			this.numFloors = numFloors;
		}
		else {
			System.out.println("\nError: The building must have at least two floors.");
		}
	}
	
	/**
	 * Returns this building's bottom floor's value
	 * 
	 * @return  bottomFloor
	 */
	public int getBottomFloor() {
		return bottomFloor;
	}
	
	/**
	 * Sets this building's {@link #bottomFloor}
	 * 
	 * @param  bottomFloor  This building's bottom floor
	 */
	private void setBottomFloor(int bottomFloor) {
		this.bottomFloor = bottomFloor;
	}
	
	/**
	 * Returns this building's customer list
	 * 
	 * @return  customerList
	 */
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	/**
	 * Returns this building's elevator object reference
	 * 
	 * @return  elevator
	 */
	public Elevator getElevator() {
		return elevator;
	}
	
	/**
	 * Sets this building's {@link #elevator}
	 * 
	 * @param  elevator  This building's elevator
	 */
	private void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}
	// Getters and Setters END
	
	/**
	 * Returns a summary of this building's properties in a readable format.
	 * 
	 * @return  objString
	 */
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