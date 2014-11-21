package cw3;

import java.util.ArrayList;

public class Elevator {
	private int numFloors = 0;
	private ArrayList<Customer> registerList = new ArrayList<Customer>();
	private int currentFloor = 0;
	private int topFloor = 0;
	private int bottomFloor = 0;
	private String direction = "up";
	private int maxPersons = 5;
	
	public Elevator(int numFloors, int bottomFloor) {
		setBottomFloor(bottomFloor);
		setNumFloors(numFloors);
		setCurrentFloor(bottomFloor);
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
	
	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}
	// Getters and Setters END
	
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
		//customer.setInElevator(true);
		registerList.add(customer);
	}
	
	public void customerLeaves(Customer customer) {
		//customer.setInElevator(false);
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
