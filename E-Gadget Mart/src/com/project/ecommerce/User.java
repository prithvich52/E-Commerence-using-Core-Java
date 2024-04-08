package com.project.ecommerce;

import java.util.Scanner;

public class User {
	public static String again;

	public static void getUserChoice() {
		System.out.println("Press 1 - New Registration \n Press 2- For User Login \n");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Choice");
		int choice = scanner.nextInt();
		switch (choice) {

		case 1:
			System.out.println("---------User Registration Page -----------");
			UserDetails.getUserDetails();

		case 2:
			System.out.println("---------User Login Page -----------");
			UserDetails.userLogin();
			break;


		default:
			System.out.println("Invalid Choice");

		}
	}
}
