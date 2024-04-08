package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Guest {
	public static void getGuestDetails() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Guest name ->");
		String guestname = scanner.next();

		System.out.println("Enter Guest email ->");
		String guestemail = scanner.next();
		try {
			Connection connection = Common.getConnection();
			String sql = "insert into guest (guestname,guestemail)" + "values (?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			prepareStatement.setString(1, guestname);
			prepareStatement.setString(2, guestemail);

			prepareStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("Guest details inserted sucessfully");
	}
}
