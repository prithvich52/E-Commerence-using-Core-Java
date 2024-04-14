package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cart {
	public static String again;

	public static void addToCart() {
		String name = null;
		String desc = null;
		int price = 0;
		int id = 0;
		String again = null;
		int productquantity = 0;
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("------------------------------------- Add to Cart -----------------------------------------------");
			System.out.println("To Add Proudct to Cart, Enter your username ->");
			String username = scanner.next();
			System.out.println("Enter product id ->");
			int productid = scanner.nextInt();
			System.out.println("Enter product quantity ->");
			int quantity = scanner.nextInt();

			Connection connection = Common.getConnection();
			String sql = "select * from product where productid =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productid);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				name = set.getString("productname");
				desc = set.getString("productdesc");
				price = set.getInt("productprice");
				id = set.getInt("productid");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement("insert into cart (productid,"
					+ "productname,productdesc,productprice," + "productquantity,username) values (?,?,?,?,?,?)");

			preparedStatement2.setInt(1, id);
			preparedStatement2.setString(2, name);
			preparedStatement2.setString(3, desc);
			preparedStatement2.setInt(4, price);
			preparedStatement2.setInt(5, quantity);
			preparedStatement2.setString(6, username);

			preparedStatement2.execute();
			
			String sql3 = "select productquantity from product where productid =?";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, productid);
			ResultSet set3 = preparedStatement3.executeQuery();
			while (set3.next()) {
				productquantity = set3.getInt("productquantity");
				//System.out.println("Productquantity is ->" + productquantity);
			}
			
			String sql4 = "select productquantity from cart where productid =?";
			PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
			preparedStatement4.setInt(1, productid);
			ResultSet set4 = preparedStatement4.executeQuery();
			
			while (set4.next()) {
				int cQuantiy = set4.getInt("productquantity");
				//System.out.println(" Cart pruduct quantity- " + cQuantiy);
				productquantity = productquantity - cQuantiy;
				//System.out.println("Final Product quantity is -" + productquantity);
			
			}
			String sql5 = "update product set productquantity =? where productid =?";
			PreparedStatement preparedStatement5 = connection.prepareStatement(sql5);
			preparedStatement5.setInt(1, productquantity);
			preparedStatement5.setInt(2, productid);
			preparedStatement5.executeUpdate();
			System.out.println("Do You want to Buy Again");
			System.out.println("Press Y for yes and No for N");
			again = scanner.next();

			if (again.equalsIgnoreCase("Y")) {
				addToCart();
			} else {
				System.out.println("Product Added to cart sucessfully");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cart.viewCart();

	}

	public static void viewCart() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("---------------------------------------- View Cart ---------------------------------------------");
			System.out.println("To view Cart, Enter your username ->");
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
		System.out.println("Do you want to continue ?");
		System.out.println("Press Y for Yes and N for No");
		again = scanner.next();

		if (again.equalsIgnoreCase("Y")) {
			BuyCart.buyProduct();
		} else {
			System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
		}

	}

	public static void main(String[] args) {
		Cart.viewCart();
	}
}
