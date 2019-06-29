package com.indraphan.learn.ws.exceptions;

public class UserServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6165767316989092689L;

	public UserServiceException(String message) 
	{
		super(message);
	}
}
