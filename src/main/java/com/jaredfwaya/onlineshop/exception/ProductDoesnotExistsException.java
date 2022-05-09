package com.jaredfwaya.onlineshop.exception;

public class ProductDoesnotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProductDoesnotExistsException(String message) {
		super(message);
	}
}
