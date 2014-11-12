package cw3;

import java.util.Scanner;

public class AppDriver {
	public static void main(String[] args) {
		int floors = 0, customers = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the world's most efficient elevator!");
		System.out.print("Enter the number of floors: ");
		floors = input.nextInt();
		System.out.print("Enter the number of customers: ");
		customers = input.nextInt();
		Building building = new Building(floors, customers);
		System.out.print("\nbuilding object:\n-----------------\n\n" + building);
		input.close();
	}

}