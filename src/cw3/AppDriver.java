package cw3;

import java.util.Scanner;

public class AppDriver {
	public static void main(String[] args) {
		int floors = 0, customers = 0, bottomFloor = -2, movesDefault = -1, movesImproved = -1;
		boolean annotations = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Liran and Shay's elevator!");
		
		// WORK IN PROGRESS - START
		boolean typeMismatch;
		boolean illegalInput;
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the number of floors: ");
			if(input.hasNextInt()) {
				floors = input.nextInt();
				typeMismatch = false;
			}
			if(input.nextInt() > 1) {
				illegalInput = false;
			}
			if(!input.hasNextInt()) {
				System.out.print("\nThe number of floors must be an integer.");
			}
			else {
				if(input.nextInt() <= 1) {
					System.out.print("\nThe building must have at least 2 floors to be equipped with an elevator.");
				}
			}
			System.out.println(" Please try again.");
			input.nextLine();
		}
		while(!typeMismatch && !illegalInput);
		// WORK IN PROGRESS - END
		
		do {
			System.out.print("Enter the number of customers: ");
			if(input.hasNextInt()) {
				customers = input.nextInt();
			}
			else {
				System.out.println("\nThe number of customers must be an integer.");
				input.nextLine();
			}
		}
		while(customers == 0);
		
		input.close();
		
		Building building = new Building(floors, customers, bottomFloor);
		building.getElevator().setAnnotations(annotations);
		if(annotations) {
			System.out.print("\nbuilding object:\n-----------------\n\n" + building);
		}
		movesDefault = building.getElevator().startElevatorDefaultStrategy();
		
		//System.out.print("\nbuilding object:\n-----------------\n\n" + building);
		building.getElevator().setAllCustomersToNotFinished();
		movesImproved = building.getElevator().startElevatorImprovedStrategy();
		
		System.out.print("\nDefault strategy elevator served the customers in " + movesDefault + " moves.");
		System.out.print("\nImproved strategy elevator served the customers in " + movesImproved + " moves.");
		// Re-populate and re-run for testing.
//		building.populateCustomerList(customers);
//		building.getElevator().setCustomerList(building.getCustomerList());
//		System.out.print("\nbuilding object:\n-----------------\n\n" + building); // for testing
//		building.getElevator().startElevatorImprovedStrategy(); // for testing
	}
	
	//private static void getInt
}