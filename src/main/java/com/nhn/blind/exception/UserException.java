package com.nhn.blind.exception;

public class UserException extends RuntimeException {
	private static final long serialVersionUID = 3946918250337154685L;

	public UserException() {
		super("userException");
	}

	public UserException(String message) {
		super(message);
	}
}
