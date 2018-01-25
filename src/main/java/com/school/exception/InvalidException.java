package com.school.exception;

/**
 *
 * @author daeyun-jang
 *
 */
public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidException(String message) {
		super(message);
	}

}
