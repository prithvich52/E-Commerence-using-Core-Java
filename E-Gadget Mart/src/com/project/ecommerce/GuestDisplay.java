package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDisplay {

	public static void displayProductDetails() {

		try {
			Connection connection = Common.getConnection();
			String sql1 = "select * from product";// where product id =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql1);

			ResultSet set = prepareStatement.executeQuery();
			while (set.next()) {
				System.out.println("Product Id is ->" + set.getInt(1));
				System.out.println("Product Name is ->" + set.getString(2));
				System.out.println("Product Description is ->" + set.getString(3));
				System.out.println("Product Price is ->" + set.getInt(4));
				System.out.println("Product quantity is ->" + set.getInt(5));
				System.out.println("---------------------------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Prducts details displayed sucessfully");
	}


}
