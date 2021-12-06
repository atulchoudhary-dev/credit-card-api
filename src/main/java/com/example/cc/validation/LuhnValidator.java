package com.example.cc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validate card number with Luhn 10 Algorithm
 *
 */
public class LuhnValidator implements ConstraintValidator<LuhnValidation, String> {
	public boolean isValid(String cardNumber, ConstraintValidatorContext cxt) {
		return checkLuhn(cardNumber);
	}

	static boolean checkLuhn(String cardNumber) {
		boolean isValid = false;
		if (cardNumber != null && !cardNumber.trim().isEmpty()) {
			// Remove dashes and spaces
			String number = cardNumber.replaceAll("\\s", "").replaceAll("-", "");
			int nDigits = number.length();

			int nSum = 0;
			boolean isSecond = false;
			for (int i = nDigits - 1; i >= 0; i--) {

				int d = number.charAt(i) - '0';

				if (isSecond == true)
					d = d * 2;

				// We add two digits to handle
				// cases that make two digits
				// after doubling
				nSum += d / 10;
				nSum += d % 10;

				isSecond = !isSecond;
			}
			isValid = (nSum % 10 == 0);
		}

		return isValid;
	}
}
