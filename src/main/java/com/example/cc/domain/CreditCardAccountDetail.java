package com.example.cc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Additional details of credit card account.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditCardAccountDetail extends CreditCardAccount {

	private String currency;
	private String balance;

}
