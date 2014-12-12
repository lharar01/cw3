/**
 * This application uses the classes Building, Elevator and Customer to simulate elevator operation in an American-style
 * building (no 13th floor). The user inputs The number of floors, the number of customers, the bottom floor and whether
 * or not they would like annotations. The customers in the building are chosen at random and provided to the elevator,
 * alongside the number of floors and the bottom floor.
 * The elevator simulates 2 strategies:
 * - Default strategy: Start at the bottom, go to the top and back to the bottom.
 * - Improved strategy: Start from current position and direction; change direction when finished serving customers in that
 * direction; and only go as far as necessary.
 * 
 * These two methods (the strategies) return the number of moves it took them to serve all customers.
 * The results are then printed to the console.
 * 
 * @author Liran Harary &amp; Shay Meshulam
 */
package cw3;

import java.util.Scanner;

public class AppDriver {
	private static int floors = 0, customers = 0, bottomFloor = -2, movesDefault = -1, movesImproved = -1;
	private static boolean annotations = false;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);		
		System.out.println("--==Welcome to Liran and Shay's elevator!==--\n");
		// Get necessary input from user
		getInput(input);
		input.close();
		
		// Create a new Building object using the user's input.
		Building building = new Building(floors, customers, bottomFloor);
		// Set the Building's Elevator's annotations value to the user's choice.
		building.getElevator().setAnnotations(annotations);
		// If annotations are turned on, print the Building object that was just created.
		if(annotations) {
			System.out.print("\nbuilding object:\n-----------------\n\n" + building);
		}
		// Start the Building's Elevator at default strategy and store the returned number of moves in movesDefault. 
		movesDefault = building.getElevator().startElevatorDefaultStrategy();
		
		// Set all the Customers in the Elevator's customerList to "not finished" (isFinished == false).
		building.getElevator().setAllCustomersToNotFinished();
		// Start the Building's Elevator at improved strategy and store the returned number of moves in movesImproved.
		movesImproved = building.getElevator().startElevatorImprovedStrategy();
		
		// Print messages to show the number of moves each strategy took to serve all Customers in the Building.
		System.out.print("\nDefault strategy elevator served the customers in " + movesDefault + " moves.");
		System.out.print("\nImproved strategy elevator served the customers in " + movesImproved + " moves.");
	}
	
	/**
	 * Gets from the user the input that is required for this application to work.
	 * The input gets stored in the class variables: #floors, #customers, #bottomFloor and #annotations.
	 * 
	 * @param input
	 */
	private static void getInput(Scanner input) {
		boolean typeMismatch;
		boolean illegalInput;
		
		// The following "do-while" block does the following:
		// - assumes typeMismatch and illegalInput to be true unless proven otherwise.
		// - Keeps asking for the number of floors while there are errors (wrong type or floors <= 1).  
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
		// The following "do-while" block does the following:
		// - assumes typeMismatch and illegalInput to be true unless proven otherwise.
		// - Keeps asking for the number of customers while there are errors (wrong type or customers <= 0).
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

		// The following "do-while" block does the following:
		// - assumes typeMismatch to be true unless proven otherwise.
		// - Keeps asking for the number of the bottom floor while a valid type has not been received.
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
		
		// Ask the user if they want annotations to be printed while the application runs. Store true in the variable annotations if the answer is
		// "yes or "y".  
		System.out.print("Would you like annotations? ");
		String annotationsAnswer = input.nextLine(); 
		if(annotationsAnswer.equals("yes") || annotationsAnswer.equals("y")) {
			annotations = true;
		}
	}
}