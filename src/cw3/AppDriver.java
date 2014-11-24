package cw3;

import java.util.Scanner;

public class AppDriver {
	private static int floors = 0, customers = 0, bottomFloor = -2, movesDefault = -1, movesImproved = -1; // break to separate lines?
	private static boolean annotations = false;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
//		System.out.print("Would you like annotations? ");
//		String annotationsAnswer = input.nextLine(); 
//		if(annotationsAnswer == "yes" || annotationsAnswer == "y") {
//			annotations = true;
//		}
//		input.close();
		
		System.out.println("--==Welcome to Liran and Shay's elevator!==--\n");
		
		getInput(input);
		
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
	
	private static void getInput(Scanner input) {
		boolean typeMismatch;
		boolean illegalInput;
		
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the number of floors: ");
			if(input.hasNextInt()) {
				floors = input.nextInt();
				typeMismatch = false;
				if(floors > 1) {
					illegalInput = false;
				}
			}			
			if(typeMismatch || illegalInput) {
				if(typeMismatch) {
					System.out.print("\nThe number of floors must be an integer.");
				}
				else {
					System.out.print("\nThe building must have at least two floors to be equipped with an elevator.");
				}
				System.out.println(" Please try again.");
				input.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		System.out.println("");
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the number of customers: ");
			if(input.hasNextInt()) {
				customers = input.nextInt();
				typeMismatch = false;
				if(customers > 0) {
					illegalInput = false;
				}
			}			
			if(typeMismatch || illegalInput) {
				if(typeMismatch) {
					System.out.print("\nThe number of customers must be an integer.");
				}
				else {
					System.out.print("\nThere must be at least one customer in order to use the elevator.");
				}
				System.out.println(" Please try again.");
				input.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		do {
			typeMismatch = true;
			System.out.print("Enter the bottom floor: ");
			if(input.hasNextInt()) {
				bottomFloor = input.nextInt();
				typeMismatch = false;
			}			
			if(typeMismatch) {
				System.out.print("\nThe bottom floor must be an integer. Please try again.");
				input.nextLine();
			}
		}
		while(typeMismatch);
		
		input.nextLine();
		
		System.out.print("Would you like annotations? ");
		String annotationsAnswer = input.nextLine(); 
		if(annotationsAnswer.equals("yes") || annotationsAnswer.equals("y")) {
			annotations = true;
		}
	}
}