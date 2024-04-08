package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminProductInsert {

	public static String again;

	public static void getProductDetails() {
		Scanner scanner = new Scanner(System.in);
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

	public static void displayProductDetails() {
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = Common.getConnection();
			String sql1 = "select * from product";// where product id =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);

			ResultSet set = prepareStatement.executeQuery();
			while (set.next()) {
				System.out.println("******************************************************************************");
				System.out.println("*---------------------- Proudct list ---------------------------*");
				System.out.println("******************************************************************************");
				System.out.println(" ProductId" + "\t" + "Product Name" + "\t\t" + "Product Description" + "\t\t"
						+ "Product Price" + "\t" + "Product quantity \t ");
				System.out.println("------------------------------------------------------------------------------");

				while (set.next()) {
					System.out.printf("%10s %18s %35s %10s %15s", set.getInt(1), set.getString(2), set.getString(3),
							set.getInt(4), set.getInt(5));
					System.out.println();
					System.out.println("--------------------------------------------------------------------------");

					System.out.println("Prducts details displayed sucessfully");

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
