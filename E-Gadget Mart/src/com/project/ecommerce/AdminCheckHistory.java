package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminCheckHistory {

	public static void viewCart() {
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Enter your username ->");
			String username = scanner.next();
			Connection connection = Common.getConnection();
			String sql1 = "select * from cart where username =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1, username);
			ResultSet set = prepareStatement.executeQuery();
			while (set.next()) {
				//
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("-----------------------------User Cart Details----------------------------------");
				// System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Product Id is ->" + set.getInt(1));
				System.out.println("Product Name is ->" + set.getString(2));
				System.out.println("Product Description is ->" + set.getString(3));
				System.out.println("Product Price is ->" + set.getInt(4));
				System.out.println("Product quantity is ->" + set.getInt(5));
				System.out.println("User Name is ->" + set.getString(6));
				System.out.println("--------------------------------------------------------------------------------");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void buyProduct() {
		String name = null;
		String desc = null;
		int price = 0;
		int id = 0;
		int quantity = 0;
		String again = null;
		long total = 0l;
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------Buy from cart----------------------");
		System.out.println("Enter your username ->");
		String username = scanner.next();
		Connection con = Common.getConnection();
		String sql = "insert into buy (productid,productname,productdesc,productprice,productquantity,username) "
				+ "select productid,productname,productdesc,productprice,productquantity,username"
				+ " from cart where username =?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.execute();

			Connection connection = Common.getConnection();
			String sql4 = "UPDATE buy set total=productprice*productquantity where username =?;";
			PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
			preparedStatement4.setString(1, username);
			preparedStatement4.executeUpdate();
			System.out.println("----------------------------------------------------------------");
			System.out.println("***----------Proudct added to Buy cart sucessfully--------***");
			System.out.println("----------------------------------------------------------------");
			System.out.println("Do you want to continue ?");
			System.out.println("Press Y for Yes and N for No");
			again = scanner.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void displaybillDetails() {
		long total = 0l;
		Scanner scanner1 = new Scanner(System.in);
		try {

			System.out.println("Enter your username ->");
			String username = scanner1.next();
			Connection connection = Common.getConnection();
			String sql1 = "select * from buy where username =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1, username);

			ResultSet set = prepareStatement.executeQuery();

			System.out.println("******************************************************************************");
			System.out.println("*----------------------  Welcome To E-Gadget Mart ----------------------------*");
			System.out.println("*******************************************************************************");
			System.out.print("\nSr.No.\tProduct ID\tProduct Name\tPrice\tQuantity \tTotal Price\n");
			// System.out.println("SrNo" +"\t"+ "Product id"+ "\t"+ "Product Name" + "\t" +
			// "Price" +"\t"+ "Quantity"+ "\t"+ "Total Price" +"\t");
			System.out.println("-------------------------------------------------------------------------------");

			while (set.next()) {
				System.out.printf("\n%5s %7s %22s %8s  %5s %18s  ", set.getInt(1), set.getInt(3), set.getString(4),
						set.getInt(6), set.getInt(7), set.getLong(8));
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------");

			}
			String sql5 = "select sum(total) as amount from buy where username =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql5);
			preparedStatement.setString(1, username);
			ResultSet set2 = preparedStatement.executeQuery();
			while (set2.next()) {
				total = set2.getLong("amount");
			}
			System.out.println();
			System.out.println("                                                  Total Amount =" + total + "/- Rs.");
			System.out.println("");

			System.out.println(" ......................Thank you For Visiting.................................");
			System.out.println("");
			System.out.println("******************************************************************************");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
