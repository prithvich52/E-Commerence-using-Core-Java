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
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("-------------Add to Cart --------------");
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
			System.out.println("----------------------View Cart---------------------");
			System.out.println("To view Cart, Enter your username ->");
			String username = scanner.next();
			Connection connection = Common.getConnection();
			String sql1 = "select * from cart where username =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);
			prepareStatement.setString(1, username);
			ResultSet set = prepareStatement.executeQuery();
			while (set.next()) {
				System.out.println("---------------------------------------------------------------");
				System.out.println("-------------------------Your Cart Details--------------------------------------");
				System.out.println("---------------------------------------------------------------");
				System.out.println("Product Id is ->" + set.getInt(1));
				System.out.println("Product Name is ->" + set.getString(2));
				System.out.println("Product Description is ->" + set.getString(3));
				System.out.println("Product Price is ->" + set.getInt(4));
				System.out.println("Product quantity is ->" + set.getInt(5));
				System.out.println("User Name is ->" + set.getString(6));
				System.out.println("---------------------------------------------------------------");

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

}
