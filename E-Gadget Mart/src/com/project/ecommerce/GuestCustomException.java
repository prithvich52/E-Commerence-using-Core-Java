package com.project.ecommerce;

public class GuestCustomException extends RuntimeException {

	
	private String message;

	public GuestCustomException(String message) {
		super(message);
		//this.message = message;
	}
}

