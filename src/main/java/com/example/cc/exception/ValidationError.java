package com.example.cc.exception;

import lombok.Data;

/**
 * contains API validation error.
 *
 */
@Data
public class ValidationError {

	/**
	 * Field name where validation exception occured
	 */
	private String fieldName;

	/**
	 * Invalid/rejected field value
	 */
	private String fieldValue;

	/**
	 * Validation error message
	 */
	private String additonalInfo;
}
