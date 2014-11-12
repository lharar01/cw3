package cw3;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	private int numFloors = 0;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private Elevator elevator = null;
	
	public Building(int numFloors, int numCustomers) {
		this.numFloors = numFloors;
		populateCustomerAL(numCustomers);
		this.elevator = new Elevator(numFloors);
		// System.out.print(""); - FOR TESTING ONLY
	}
	
	private void populateCustomerAL(int numCustomers) {
		Random rand = new Random();
		for(int i=0; i<numCustomers; i++) {
			int rand1, rand2;
			rand1 = rand.nextInt(numFloors);
			do {
				rand2 = rand.nextInt(numFloors);
			}
			while(rand1 == rand2);
			customerList.add(new Customer(rand1, rand2));
		}
	}
	
	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
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