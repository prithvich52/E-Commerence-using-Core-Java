package com.project.ecommerce;

import java.util.Scanner;

public class User {
	public static String again;
	public static void getUserChoice () {
		System.out.println("Press 1 - New Registration \n Press 2- For User Login \n");
		Scanner scanner = new Scanner (System.in);
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
//		case 3 : 
//			System.out.println("---------User View Product Item as Sorted Order-----------");
//			DisplayProduct.displayProductDetails();
//
//			
//		case 4:
//			System.out.println("---------Add to cart-----------");
//				Cart.addToCart();
//			
//		case 5:
//			System.out.println("---------View cart-----------");
//				Cart.viewCart();
//				
//		case 6:
//			System.out.println("--------- Purchase the Items -----------");
//			BuyCart.buyProduct();	
//			BuyCart.displaybillDetails();
			
		default :
			System.out.println("Invalid Choice");
			
		}
	}
}

