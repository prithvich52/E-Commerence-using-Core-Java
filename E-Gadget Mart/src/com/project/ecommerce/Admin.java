package com.project.ecommerce;

import java.util.Scanner;

public class Admin {
	
		public static void getAdminChoice () {
			System.out.println("Press 1 -  Admin Login ");
			Scanner scanner = new Scanner (System.in);
			System.out.println("Enter your Choice");
			int choice = scanner.nextInt();
			switch (choice) {
			
			case 1:
				System.out.println("---------Admin Login -----------");
					AdminDetails.adminLogin();
				break;
				
			default :
				System.out.println("Invalid Choice");
				
			}
		}
	}


