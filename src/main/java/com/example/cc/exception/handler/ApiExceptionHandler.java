package com.example.cc.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cc.auth.exception.UserAlreadyExistsException;
import com.example.cc.exception.ApiError;
import com.example.cc.exception.CreditCardAlreadyExistsException;
import com.example.cc.exception.ValidationError;

/**
 * Handler for application exceptions
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {

	Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	/**
	 * Handle input validation exception
	 * 
	 * @param {{@link MethodArgumentNotValidException}} exception to handle
	 * @return {@link ResponseEntity} containing API error response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.error("Validation Exception ", ex);
		List<ValidationError> validationErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			ValidationError validationError = new ValidationError();
			String fieldName = ((FieldError) error).getField();
			Object rejectedValue = ((FieldError) error).getRejectedValue();
			String fieldValue = (rejectedValue != null) ? rejectedValue.toString() : null;
			validationError.setFieldName(fieldName);
			validationError.setFieldValue(fieldValue);
			validationError.setAdditonalInfo(error.getDefaultMessage());
			validationErrors.add(validationError);
		});

		ApiError exceptionResponse = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
				validationErrors);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle credit card already exists exception
	 * 
	 * @param @param {{@link CreditCardAlreadyExistsException}} exception to handle
	 * @return {@link ResponseEntity} containing API error response
	 */
	@ExceptionHandler(CreditCardAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleCreditCardAlreadyExistsException(CreditCardAlreadyExistsException ex) {
		logger.error("Credit Card Already exist Exception ", ex);
		ApiError exceptionResponse = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}

	/**
	 * Handle user already exists exception
	 * 
	 * @param @param {{@link UserAlreadyExistsException}} exception to handle
	 * @return {@link ResponseEntity} containing API error response
	 */
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		logger.error("User Already exist Exception ", ex);
		ApiError exceptionResponse = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}

	/**
	 * Handle any other unknown exception
	 * 
	 * @param @param {{@link Exception}} exception to handle
	 * @return {@link ResponseEntity} containing API error response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleRuntimeException(Exception ex) {
		logger.error("Internal Server Error ", ex);
		ApiError exceptionResponse = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
