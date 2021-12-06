package com.example.cc.dao;

import java.util.List;

import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;

/**
 * Data access layer for credit card operation
 *
 */
public interface CreditCardDAO {

	/**
	 * Add a new a credit card
	 * 
	 * @param {@link CreditCardAccount} to be added
	 * @return {@link CreditCardAccountDetail} containing credit card details of
	 *         newly created card
	 */
	CreditCardAccountDetail addCreditCard(CreditCardAccount creditCardAccount);

	/**
	 * Get details of all credit cards in the system
	 * 
	 * @return {@link List} list of all credit cards
	 */
	List<CreditCardAccountDetail> findAllCreditCardAccounts();

}
