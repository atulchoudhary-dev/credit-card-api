package com.example.cc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.cc.validation.CardNumberValidationGroup;
import com.example.cc.validation.LuhnValidation;

import lombok.Data;

/**
 * Base class of Credit Card Account.
 *
 */
@Data
public class CreditCardAccount {

	@NotBlank(message = "Name is mandatory")
	@Size(max = 15, message = "Must be 15 characters or less")
	private String userName;

	@NotBlank(message = "Card Number is mandatory")
	@Pattern(regexp = "^[0-9-\\s]{11,19}$", message = "Card number can only have number[0-9], dash [-], space[] and should be between 11 to 19 characters !")
	@LuhnValidation(message = "Provide Card Number is not Valid ", groups = CardNumberValidationGroup.class)
	private String cardNumber;

	@NotNull(message = "Credit Limit is mandatory")
	@Pattern(regexp = "^\\d+(?:\\.\\d{0,2})$", message = "InValid Currency value. Should be in the format 00.00")
	private String creditLimit;

}
