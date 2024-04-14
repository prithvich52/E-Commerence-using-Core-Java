package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserProudctHistory {

	public static void getUserProductHistory() {
		Scanner scanner = new Scanner (System.in);
		System.out.println("To exit, enter your username");
		String username = scanner.next();
		Connection connection =Common.getConnection();
		String sql= "INSERT INTO purchasehistory select * from buy where username =?";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,username);
			preparedStatement.execute();
		
		String sql1="delete from buy where username =?";
		PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
		preparedStatement1.setString(1,username);
		preparedStatement1.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void viewPurchaseHistory() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("---------------------------------------- View Cart ---------------------------------------------");
			System.out.println("To see purchase History, Enter username ->");
			String username = scanner.next();
			Connection connection = Common.getConnection();
			String sql1 = "select * from purchasehistory where username =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1, username);
			ResultSet set = prepareStatement.executeQuery();

			System.out.println(
					"************************************************************************************************************************");
			System.out.println(
					"*------------------------------------------------ Cart Proudct list ---------------------------------------------------*");
			System.out.println(
					"************************************************************************************************************************");
			System.out.println("Username"+"\t"+"ProductId" + "\t" + "Product Name" + "\t" + "Product Description" +"\t"
					+ "Product Price" + "\t" + "Product quantity \t "+"Total");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------");

			while (set.next()) {
				System.out.printf("%7s %10s %25s %26s %12s %12s %17s ",set.getString(2), set.getInt(3), set.getString(4), set.getString(5),
						set.getInt(6), set.getInt(7),set.getLong(8));
				System.out.println();
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public static void adminViewCart() {
			Scanner scanner = new Scanner(System.in);
			try {
				System.out.println("---------------------------------------- View Cart ---------------------------------------------");
				System.out.println("To view Cart History, Enter username ->");
				String username = scanner.next();
				Connection connection = Common.getConnection();
				String sql1 = "select * from cart where username =?";
				PreparedStatement prepareStatement = connection.prepareStatement(sql1);
				prepareStatement.setString(1, username);
				ResultSet set = prepareStatement.executeQuery();

				System.out.println(
						"***********************************************************************************************************");
				System.out.println(
						"*---------------------------------------- Cart Proudct list ----------------------------------------------*");
				System.out.println(
						"***********************************************************************************************************");
				System.out.println(" ProductId" + "\t" + "Product Name" + "\t" + "Product Description" + "\t"
						+ "Product Price" + "\t" + "Product quantity \t ");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------");

				while (set.next()) {
					System.out.printf("%10s %18s %15s %15s %15s", set.getInt(1), set.getString(2), set.getString(3),
							set.getInt(4), set.getInt(5));
					System.out.println();
					System.out.println(
							"----------------------------------------------------------------------------------------------------------");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		UserProudctHistory.viewPurchaseHistory();
	}
}
