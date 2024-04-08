package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Product {

	int productid;
	String productname;
	String description;
	int productprice;
	int productquatity;
	public static String again;

	public static void addNewProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------Add New Product---------------------");
		System.out.println("Enter product id ->");
		int productid = scanner.nextInt();

		System.out.println("Enter product name ->");
		String productname = scanner.next();

		System.out.println("Enter product description ->");
		String productdesc = scanner.next();

		System.out.println("Enter product price ->");
		int productprice = scanner.nextInt();

		System.out.println("Enter product quantity ->");
		int productquantity = scanner.nextInt();
		try {
			Connection connection = Common.getConnection();
			String sql = "insert into product (productid, productname,productdesc,productprice,productquantity)"
					+ "values (?,?,?,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, productid);
			prepareStatement.setString(2, productname);
			prepareStatement.setString(3, productdesc);
			prepareStatement.setInt(4, productprice);
			prepareStatement.setInt(5, productquantity);

			prepareStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Prducts details inserted sucessfully");

	}

	public static void updateProductlist() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------");
		System.out.println("Enter Username to update quantity after buying");
		int productid = scanner.nextInt();
		int productquantity = 0;
		// int productquantity1=0;
		try {
			Connection connection = Common.getConnection();
			String sql = "select productquantity from product where productid =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productid);
			ResultSet set1 = preparedStatement.executeQuery();

			String sql1 = "select productquantity from buy where productid =?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, productid);
			ResultSet set2 = preparedStatement1.executeQuery();
			while (set1.next()) {
				productquantity = set1.getInt("productquantity");
				System.out.println("Productquantity is ->" + productquantity);
			}

			while (set2.next()) {
				int cQuantiy = set2.getInt("productquantity");
				System.out.println(" Cart pruduct quantity- " + cQuantiy);
				productquantity = productquantity - cQuantiy;
				System.out.println("Final Product quantity is -" + productquantity);

			}
			String sql3 = "update product set productquantity =? where productid =?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql3);
			preparedStatement2.setInt(1, productquantity);
			preparedStatement2.setInt(2, productid);
			preparedStatement2.executeUpdate();
			// System.out.println("Update sucessfully.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateAdminQuantityList() {
		int productquantity = 0;
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Enter product id ->");
		int productid = scanner.nextInt();

		System.out.println("Enter product quantity->");
		int productquantity1 = scanner.nextInt();

		try {
			Connection connection = Common.getConnection();
			String sql = "select productquantity from product where productid =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productid);
			ResultSet set1 = preparedStatement.executeQuery();
			while (set1.next()) {
				productquantity = set1.getInt("productquantity");
				productquantity = productquantity + productquantity1;
				System.out.println("Productquantity is ->" + productquantity);
			}
			String sql3 = "update product set productquantity =? where productid =?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql3);
			preparedStatement2.setInt(1, productquantity);
			preparedStatement2.setInt(2, productid);
			preparedStatement2.executeUpdate();
			System.out.println("Update sucessfully.");
			System.out.println("You want to Buy Again");
			System.out.println("Press Y for yes and No for N");
			again = scanner.next();

			if (again.equalsIgnoreCase("Y")) {
				updateAdminQuantityList();
			} else {
				System.out.println("Add to cart sucessfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
