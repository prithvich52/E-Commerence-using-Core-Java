package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BuyCart {
	public static String again;

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
		System.out.println("To buy product, enter your username ->");
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
		BuyCart.displaybillDetails();

	}

	public static void displaybillDetails() {
		long total = 0l;
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------Bill Display Page--------------------");
		try {

			System.out.println("To get bill enter your username ->");
			String username = scanner.next();
			Connection connection = Common.getConnection();
			String sql1 = "select * from buy where username =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1, username);

			ResultSet set = prepareStatement.executeQuery();

			System.out.println("******************************************************************************");
			System.out.println("*----------------------  Welcome To E-Gadget Mart ---------------------------*");
			System.out.println("******************************************************************************");
			System.out.println(" SrNo" + "\t" + "Product id" + "\t" + "Product Name" + "\t" + "Price" + "\t"
					+ "Quantity" + "\t" + "Total" + "\t ");
			System.out.println("------------------------------------------------------------------------------");

			while (set.next()) {
				System.out.println(set.getInt(1) + "\t" + set.getInt(3) + "\t" + set.getString(4) + "\t" + set.getInt(6)
						+ "\t" + set.getInt(7) + "\t" + set.getLong(8) + "\t");

				System.out.println("--------------------------------------------------------------------------");

			}
			String sql5 = "select sum(total) as amount from buy where username =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql5);
			preparedStatement.setString(1, username);
			ResultSet set2 = preparedStatement.executeQuery();
			while (set2.next()) {
				total = set2.getLong("amount");
			}
			System.out.println("                                                Total Amount =" + total + "/- Rs.");
			System.out.println("");

			System.out.println("                    Thank you For Visiting....");
			System.out.println("");
			System.out.println("***************************************************************************");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
