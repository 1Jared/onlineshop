package com.jaredfwaya.onlineshop.exception;

public class ImageDoesnotExistsException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public ImageDoesnotExistsException(String message) {
		super(message);
	}
}
