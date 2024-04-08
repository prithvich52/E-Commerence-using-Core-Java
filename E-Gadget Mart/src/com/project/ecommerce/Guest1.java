package com.project.ecommerce;

import java.util.Scanner;

public class Guest1 {

	public static String again;
	public static void getGuestChoice () {
		System.out.println(" Press 1 :-  View Product Items \n Press 2 :-  Purchase Items \n Press 3 :- Exit");
		System.out.println("--------------------------------------------------------------------------------");
		Scanner scanner = new Scanner (System.in);
		System.out.println("Enter your Choice");
		int choice = scanner.nextInt();
		
			
		
		switch (choice) {
		
		case 1:
			System.out.println("--------------------------View Product Items ----------------------------");
				GuestDisplay.displayProductDetails();
			break;
			
		case 2:
			System.out.println("---------------------------- Purchase Items -----------------------------");
				System.out.println("Please Register before place order");
				throw new GuestCustomException("Please Register before place order");
				
				
		case 3:
			System.out.println("Do you want to continue ?");
			System.out.println("Press Y for Yes and N for No");
			again =scanner.next();
			
			if(again.equalsIgnoreCase("Y")) {
				HomePage.mainMethod();
			}
			else {
				System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
			}
			break;
		default : 
			System.out.println("Invalid Choice");
			
}
}

	private static void exit(int i) {
		// TODO Auto-generated method stub
		
	}}
