package com.ws.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 889463817395888050L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
