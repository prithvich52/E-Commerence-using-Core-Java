package com.project.ecommerce;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	public class AdminProductInsert {

		public static String again;
	public static void getProductDetails() {
		Scanner scanner = new Scanner (System.in);
		System.out.println("Enter product id ->");
		int productid= scanner.nextInt();
		
		System.out.println("Enter product name ->");
		String productname= scanner.next();
		
		System.out.println("Enter product description ->");
		String productdesc= scanner.next();
		
		System.out.println("Enter product price ->");
		int productprice= scanner.nextInt();
		
		System.out.println("Enter product quantity ->");
		int productquantity= scanner.nextInt();
			try {
				Connection connection =Common.getConnection();
				String sql ="insert into product (productid, productname,productdesc,productprice,productquantity)"+"values (?,?,?,?,?)";
				PreparedStatement prepareStatement =connection.prepareStatement(sql);
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
		Scanner scanner = new Scanner (System.in);
		try {
			Connection connection =Common.getConnection();
			String sql1 ="select * from product";//where product id =?";
			PreparedStatement prepareStatement =connection.prepareStatement(sql1);
			
			
			ResultSet set =prepareStatement.executeQuery();
			while (set.next()) {
				
//			while(products.next()) {
//					System.out.println("\n");
//					System.out.printf("%10s %15s %8s %10s %15s",set.getInt("productid"),set.getString("productname"),
//							set.getString("productdesc"), set.getInt("productprice"), set.getInt("productquantity"));
//				}
			System.out.println("-----------------------ProductList-----------------------");
			System.out.println("Product Id is ->"+set.getInt(1));
			System.out.println("Product Name is ->"+set.getString(2));
			System.out.println("Product Description is ->"+set.getString(3));
			System.out.println("Product Price is ->"+set.getInt(4));
			System.out.println("Product quantity is ->"+set.getInt(5));
			System.out.println("---------------------------------------------------------------");
			
			System.out.println("Prducts details displayed sucessfully");
			

			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	}

