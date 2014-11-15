package cw3;

import java.util.Scanner;

public class AppDriver {
	public static void main(String[] args) {
		int floors = 0, customers = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the world's most efficient elevator!");
		
		do {
			System.out.print("Enter the number of floors: ");
			if(input.hasNextInt()) {
				floors = input.nextInt();
			}
			else {
				System.out.println("\nThe number of floors must be an integer.");
				input.nextLine();
			}
		}
		while(floors == 0);
		
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
		
		Building building = new Building(floors, customers);
		System.out.print("\nbuilding object:\n-----------------\n\n" + building);
		input.close();
	}

}