package com.example.cc.auth.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8063770068404282799L;

	public UserAlreadyExistsException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
