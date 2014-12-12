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
		Scanner scanner = new Scanner(System.in);		
		System.out.println("--==Welcome to Liran and Shay's elevator!==--\n");
		// Get necessary input from user
		getInput(scanner);
		scanner.close();
		
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
	 * @param scanner
	 */
	private static void getInput(Scanner scanner) {
		boolean typeMismatch;
		boolean illegalInput;
		
		// The following THREE "do-while" loops do the following:
		// - assumes typeMismatch and illegalInput to be true unless proven otherwise.
		// - Keeps asking for input while there are errors (wrong type or illegal input).
		
		// Gets number of floors from the user
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the number of floors: ");
			if(scanner.hasNextInt()) {
				floors = scanner.nextInt();
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
				scanner.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		System.out.println("");
		
		// Gets number of customers from the user
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the number of customers: ");
			if(scanner.hasNextInt()) {
				customers = scanner.nextInt();
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
				scanner.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		// Gets the bottom floor value from the user
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Enter the bottom floor: ");
			if(scanner.hasNextInt()) {
				bottomFloor = scanner.nextInt();
				typeMismatch = false;
				if(bottomFloor <= 1) {
					illegalInput = false;
				}
			}			
			if(typeMismatch || illegalInput) {
				if(typeMismatch) {
					System.out.print("\nThe bottom floor must be an integer.");
				}
				else {
					System.out.print("\nThe bottom floor cannot be higher than 1.");
				}
				System.out.println(" Please try again.");
				scanner.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		scanner.nextLine();
		
		// Asks the user if they want annotations to be printed while the application runs. Stores true in the variable annotations if the answer is
		// "yes" or "y".  
		System.out.print("Would you like annotations? ");
		String annotationsAnswer = scanner.nextLine(); 
		if(annotationsAnswer.equals("yes") || annotationsAnswer.equals("y")) {
			annotations = true;
		}
	}
}