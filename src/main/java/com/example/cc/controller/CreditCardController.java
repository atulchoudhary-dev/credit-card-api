package com.example.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.service.CreditCardService;
import com.example.cc.validation.ValidationSequence;

/**
 * Rest interface for credit card operations
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * Get details of all credit cards in the system
	 * 
	 * @return {@link ResponseEntity} containing credit cards
	 * 
	 */
	@RequestMapping(value = "/cards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CreditCardAccountDetail>> findAllCreditCardAccounts() {
		List<CreditCardAccountDetail> creditCardList = creditCardService.findAllCreditCardAccounts();
		return new ResponseEntity<>(creditCardList, HttpStatus.OK);
	}

	/**
	 * Add a new a credit card
	 * 
	 * @param creditCardAccount to be added
	 * @return {@link ResponseEntity} containing credit card details of newly
	 *         created card
	 */
	@RequestMapping(value = "/cards", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreditCardAccountDetail> addCreditCard(
			@Validated(ValidationSequence.class) @RequestBody CreditCardAccount creditCardAccount) {
		CreditCardAccountDetail savedCreditCard = creditCardService.addCreditCard(creditCardAccount);
		return new ResponseEntity<>(savedCreditCard, HttpStatus.CREATED);
	}

}
