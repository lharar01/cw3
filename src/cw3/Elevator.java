package cw3;

import java.util.ArrayList;

public class Elevator {
	private int numFloors = 0;
	private ArrayList<Customer> registerList = new ArrayList<Customer>();
	private int currentFloor = 0;
	private String direction = "up";
	
	public Elevator(int numFloors) {
		this.numFloors = numFloors;
	}
	
	// Getters and Setters START
	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}

	public ArrayList<Customer> getRegisterList() {
		return registerList;
	}

	public void setRegisterList(ArrayList<Customer> registerList) {
		this.registerList = registerList;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	// Getters and Setters END
	
	public void move() {
		if(direction == "up") {
			currentFloor++;
		}
		else {
			currentFloor--;
		}
	}
	
	public void customerJoins(Customer customer) {
		registerList.add(customer);
	}
	
	public void customerLeaves(Customer customer) {
		registerList.remove(customer);
	}
	
	@Override
	public String toString() {
		String objString = "";
		objString += "numFloors: " + numFloors + "\nregisterList:\n";
		for(int i=0; i<registerList.size(); i++) {
			objString += registerList.get(i);
		}
		objString += "\ncurrentFloor: " + currentFloor + "\ndirection: " + direction + "\n\n";
		return objString;
	}
}
