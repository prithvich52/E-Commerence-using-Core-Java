package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayProduct {
	public static String again; 
	public static int choice;

public static void displayProductDetails() {
		Scanner scanner = new Scanner (System.in);
		try {
			Connection connection =Common.getConnection();
			String sql1 ="select * from product";//where product id =?";
			PreparedStatement prepareStatement =connection.prepareStatement(sql1);
			
			
			ResultSet set =prepareStatement.executeQuery();
			while (set.next()) {
				
//			while(products.next()) {
//					System.out.println("\n");
//					System.out.printf("%10s %15s %8s %10s %15s",set.getInt("productid"),set.getString("productname"),
//							set.getString("productdesc"), set.getInt("productprice"), set.getInt("productquantity"));
//				}
			System.out.println("-----------------------ProductList-----------------------");
			System.out.println("Product Id is ->"+set.getInt(1));
			System.out.println("Product Name is ->"+set.getString(2));
			System.out.println("Product Description is ->"+set.getString(3));
			System.out.println("Product Price is ->"+set.getInt(4));
			System.out.println("Product quantity is ->"+set.getInt(5));
			System.out.println("---------------------------------------------------------------");
			
			System.out.println("Prducts details displayed sucessfully");
			

			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}System.out.println("Do you want to continue ?");
		System.out.println("Press Y for Yes and N for No");
		again =scanner.next();
		
		if(again.equalsIgnoreCase("Y")) {
			Cart.addToCart();
		}
		else {
			System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
		}

		
		
	}
	
	
		

//public static void main(String[] args) {
//	Scanner scanner = new Scanner (System.in);
//	System.out.println("Enter product id ->");
//	int productid= scanner.nextInt();
//	displayProductDetails();
	
	
//}
}

