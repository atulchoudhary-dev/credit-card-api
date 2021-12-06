package com.example.cc.exception;

/**
 * Credit card already exists exception
 *
 */
public class CreditCardAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8063770068404282766L;

	public CreditCardAlreadyExistsException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public CreditCardAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
