package com.example.cc.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.cc.domain.CreditCardAccount;

@RunWith(MockitoJUnitRunner.class)
public class LuhnValidatorTest {
	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeClass
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterClass
	public static void close() {
		validatorFactory.close();
	}

	@Test
	public void shouldHaveNoViolations() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void shouldHaveNoViolations_WithDashes() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		creditCardAccount.setCardNumber("7992-7398-713");
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void shouldHaveNoViolations_WithSpaces() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		creditCardAccount.setCardNumber("7992 7398 713");
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void shouldDetectInvalidCardNumber() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		creditCardAccount.setCardNumber("79927398712");
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertEquals(violations.size(), 1);
		ConstraintViolation<CreditCardAccount> violation = violations.iterator().next();
		assertEquals("Provide Card Number is not Valid ", violation.getMessage());
		assertEquals("cardNumber", violation.getPropertyPath().toString());
		assertEquals("79927398712", violation.getInvalidValue());
	}

	@Test
	public void shouldDetectInvalidCardNumber_WhenNull() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		creditCardAccount.setCardNumber(null);
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertEquals(violations.size(), 1);
		ConstraintViolation<CreditCardAccount> violation = violations.iterator().next();
		assertEquals("Provide Card Number is not Valid ", violation.getMessage());
		assertEquals("cardNumber", violation.getPropertyPath().toString());
		assertNull(violation.getInvalidValue());
	}

	@Test
	public void shouldDetectInvalidCardNumber_WhenEmpty() {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		creditCardAccount.setCardNumber("");
		Set<ConstraintViolation<CreditCardAccount>> violations = validator.validate(creditCardAccount,
				CardNumberValidationGroup.class);
		assertEquals(violations.size(), 1);
		ConstraintViolation<CreditCardAccount> violation = violations.iterator().next();
		assertEquals("Provide Card Number is not Valid ", violation.getMessage());
		assertEquals("cardNumber", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}

	private CreditCardAccount createCreditCardAccount() {
		CreditCardAccount cardAccount = new CreditCardAccount();
		cardAccount.setCardNumber("79927398713");
		cardAccount.setCreditLimit("200.00");
		cardAccount.setUserName("user name");
		return cardAccount;
	}
}