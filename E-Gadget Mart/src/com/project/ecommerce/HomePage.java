package com.project.ecommerce;

import java.util.Scanner;

public class HomePage {

	public static void mainMethod() {
		System.out.println(
				"**********************************************************************************************************");
		System.out.println(
				"*-----------------------------------  Wel-come To E-Gadget Mart -----------------------------------------*");
		System.out.println(
				"**********************************************************************************************************");

		System.out.println(" Press 1-: If yor are User ... \n Press 2-: If you are Admin...\n "
				+ "Press 3-: If you are Guest...  \n ");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		Scanner scanner = new Scanner(System.in);
		int activity = scanner.nextInt();
		switch (activity) {
		case 1:
			System.out.println(".......User Department......");
			User.getUserChoice();
			break;
		case 2:
			System.out.println(".......Admin Department .....");
			Admin.getAdminChoice();
			break;

		case 3:
			System.out.println(".......Guest Department.....");
			Guest1.getGuestChoice();
			break;

		default:
			System.out.println(" !!! Please Enter valid Input !!!");
			break;

		}
	}

	
}
