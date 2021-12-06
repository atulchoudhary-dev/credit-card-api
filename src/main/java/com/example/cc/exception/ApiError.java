package com.example.cc.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API error object. This will be return in case of 4xx and 5xx error
 *
 */
@Data
@AllArgsConstructor
public class ApiError {

	/**
	 * HTTP status code
	 */
	private int code;

	/**
	 * Error message
	 */
	private String message;

	/**
	 * Optional list of validation errorsSS
	 */
	@JsonInclude(Include.NON_NULL)
	private List<ValidationError> validationErrors;

	public ApiError(final int code, final String message) {
		this.code = code;
		this.message = message;

	}

}
