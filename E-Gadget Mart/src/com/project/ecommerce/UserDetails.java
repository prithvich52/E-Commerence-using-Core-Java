package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDetails {
	public static int choice;
	public static String again;
	
public static void getUserDetails( ) {
	Scanner scanner = new Scanner (System.in);
	System.out.println("--------------------Registration Page-------------------");
	System.out.println("Enter First Name ->");
	String userfirstname= scanner.nextLine();
	
	System.out.println("Enter Last name ->");
	String userlastname= scanner.next();
	
	System.out.println("Enter User Email ->");
	String useremail= scanner.next();
	
	System.out.println("Enter User Phone Number ->");
	long userphonenumber= scanner.nextLong();
	
	System.out.println("Enter User Id ->");
	int userid= scanner.nextInt();
	
	System.out.println("Enter Username ->");
	String username= scanner.next();
	
	System.out.println("Enter Pasword ->");
	String userpassword= scanner.next();
		try {
			Connection connection =Common.getConnection();
			String sql ="insert into user (firstname,lastname,useremail,userphonenumber,userid,username,userpassword)"+"values (?,?,?,?,?,?,?)";
			PreparedStatement prepareStatement =connection.prepareStatement(sql);
			
			prepareStatement.setString(1, userfirstname);
			prepareStatement.setString(2,userlastname);
			prepareStatement.setString(3, useremail);
			prepareStatement.setLong(4,userphonenumber);
			prepareStatement.setInt(5, userid);
			prepareStatement.setString(6, username);
			prepareStatement.setString(7, userpassword);
			
			
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("You are Sucessfully register..");
	}
	
	
public static void userLogin (){

			String username= null;
			String userpassword= null;
	try {
			Scanner scanner = new Scanner (System.in);
			System.out.println("---------------------- Welcome To User Login Page------------------");
			System.out.println("Enter Your username :- ");
			String uusername= scanner.nextLine();
			
			System.out.println("Enter Your Password :- ");
			String upassword= scanner.next();
			
			Connection connection =Common.getConnection();
			String sql ="select username,userpassword from user where username =?";
			PreparedStatement prepareStatement =connection.prepareStatement(sql);
					prepareStatement.setString(1, uusername);
			ResultSet set= prepareStatement.executeQuery();
			
			while (set.next()) {
				 username = set.getString("username");
				 userpassword= set.getString("userpassword");
//				
				 	
				if (uusername.equals(username) && upassword.equals(userpassword)) {
					System.out.println("Do you want to continue ?");
					System.out.println("Press Y for Yes and N for No");
					again =scanner.next();
					
					if(again.equalsIgnoreCase("Y")) {
						DisplayProduct.displayProductDetails();
					}
					else {
						System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
					}	
					//DisplayProduct.displayProductDetails();
						break;
						
					}
					else {
						System.out.println("Invalid details, Please enter valid details.");
						UserDetails.userLogin();
					}
				
				}
//				System.out.println(" Press 1 For User View Product Item as Sorted Order"
//						+ "\n Press 2 - For Buy Product \n Press 3 - View Cart \n Press 4 - Purchase the Items \n Press 5 - Exit");
//				
//				switch(choice) {
//				
//				case 1 : 
//					System.out.println("---------User View Product Item as Sorted Order-----------");
//					DisplayProduct.displayProductDetails();
//
//					
//				case 2:
//					System.out.println("---------Add to cart-----------");
//						Cart.addToCart();
//					
//				case 3:
//					System.out.println("---------View cart-----------");
//						Cart.viewCart();
//						
//				case 4:
//					System.out.println("--------- Purchase the Items -----------");
//					BuyCart.buyProduct();	
//					BuyCart.displaybillDetails();
//				case 5:
//					System.out.println("Do you want to continue ?");
//					System.out.println("Press Y for Yes and N for No");
//					again =scanner.next();
//					
//					if(again.equalsIgnoreCase("Y")) {
//						DisplayProduct.displayProductDetails();
//					}
//					else {
//						System.out.println("You are sucessfully exit...!!Thank you for the Visit!!");
//					}
//					break;
//			
						
//				default :
//					System.out.println("Invalid Choice");
//				}
	}
			
			
		 catch (SQLException e) {
			e.printStackTrace();
		}
				
}

public static void displayUserDetails () {
	try {
		Connection connection =Common.getConnection();
		String sql1 ="select * from user";//where product id =?";
		PreparedStatement prepareStatement =connection.prepareStatement(sql1);
		
		
		ResultSet set =prepareStatement.executeQuery();
		while (set.next()) {
			
		System.out.println("-----------------------Registered User Details------------------------");
		System.out.println("UserID is ->"+set.getInt(1));
		System.out.println("User first name is ->"+set.getString(2));
		System.out.println("User last name is ->"+set.getString(3));
		System.out.println("User email is ->"+set.getString(4));
		System.out.println("Product Phone Number is ->"+set.getLong(5));
		System.out.println("Username is ->"+set.getString(6));
		//System.out.println("User Password is ->"+set.getString(7));
		
		System.out.println("----------------------------------------------------------------------");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("------------------Regitered User Displayed Sucessfully--------------------");
}



//public static void main(String[] args) {
//	UserDetails userDetails=new UserDetails(); 
//	userDetails.userLogin();
//}

}

