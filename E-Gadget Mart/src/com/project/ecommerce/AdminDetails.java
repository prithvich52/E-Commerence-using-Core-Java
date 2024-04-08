
package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminDetails {

	public static int choice;

	public static void adminLogin() {
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Enter Admin username ->");
			String ausername = scanner.nextLine();

			System.out.println("Enter Admin Password ->");
			String apassword = scanner.next();
			Connection connection = Common.getConnection();
			String sql = "select adminusername,adminpassword from admin where adminusername =?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, ausername);

			ResultSet set = prepareStatement.executeQuery();

			while (set.next()) {
				String adminusername = set.getString("adminusername");
				String adminpassword = set.getString("adminpassword");

				if (ausername.equals(adminusername) && apassword.equals(adminpassword)) {
					System.out.println("Login Sucessful...");
					System.out.println(" Press 1 :- Add Product Items \n Press 2 :- Product List"
							+ "\n Press 3 :- Calculate Bill & Display amount to end user \n Press 4 :- Check & Update quantity  \n Press 5 :- Check "
							+ "registered user" + " \n Press 6 :- Check Particular user history");
					Scanner scanner1 = new Scanner(System.in);
					int choice = scanner1.nextInt();

					switch (choice) {
					case 1:
						System.out.println("------------------Add Product Items --------------------");
						Product.addNewProduct();
						break;

					case 2:
						System.out.println("---------------------Product List -----------------------");
						AdminProductInsert.displayProductDetails();
						break;

					case 3:
						System.out.println("------Calculate Bill & Display amount to end user--------");
						AdminCheckHistory.displaybillDetails();

						break;
					case 4:
						System.out.println("----------------Check & Update quantity  ----------------");
						Product.updateProductlist();
						Product.updateAdminQuantityList();
						break;
					case 5:
						System.out.println("----------------Check registered user  ------------------");
						UserDetails.displayUserDetails();
						break;
					case 6:
						System.out.println("------------Check particular user history ---------------");
						AdminCheckHistory.viewCart();
						// AdminCheckHistory.buyProduct();
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} else {
					System.out.println("Invalid details, Please Enter valid details");
					adminLogin();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
