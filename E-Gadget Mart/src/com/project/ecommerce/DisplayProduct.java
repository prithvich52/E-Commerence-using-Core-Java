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
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = Common.getConnection();
			String sql1 = "select * from product";// where product id =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);

			ResultSet set = prepareStatement.executeQuery();
			

				System.out.println("******************************************************************************************************");
				System.out.println("*------------------------------------------- Proudct list -------------------------------------------*");
				System.out.println("******************************************************************************************************");
				System.out.println(" ProductId" + "\t" + "Product Name" + "\t\t" + "Product Description" + "\t\t"
						+ "Product Price" + "\t" + "Product quantity \t ");
				System.out.println("------------------------------------------------------------------------------------------------------");

				while (set.next()) {
					System.out.printf("%10s %18s %35s %10s %15s", set.getInt(1), set.getString(2), set.getString(3),
							set.getInt(4), set.getInt(5));
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------------------------");

					

				}
				System.out.println("Prducts details displayed sucessfully");
			
		}catch(

	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}System.out.println("Do you want to continue ?");System.out.println("Press Y for Yes and N for No");again=scanner.next();

	if(again.equalsIgnoreCase("Y"))
	{
		Cart.addToCart();
	}else
	{
		System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
	}

	}

	public static void main(String[] args) {
//	Scanner scanner = new Scanner (System.in);
//	System.out.println("Enter product id ->");
//	int productid= scanner.nextInt();
		displayProductDetails();

	}
}
