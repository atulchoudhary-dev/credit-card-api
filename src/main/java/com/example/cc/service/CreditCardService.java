package com.example.cc.service;

import java.util.List;

import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;

/**
 * Service for Credit Card operations
 *
 */
public interface CreditCardService {

	/**
	 * Add a new a credit card
	 * 
	 * @param {@link CreditCardAccount} to be added
	 * @return {@link List} containing credit card details of newly created card
	 */
	CreditCardAccountDetail addCreditCard(CreditCardAccount creditCardAccount);

	/**
	 * Get details of all credit cards in the system
	 * 
	 * @return {@link List} list of all credit cards
	 */
	List<CreditCardAccountDetail> findAllCreditCardAccounts();

}
