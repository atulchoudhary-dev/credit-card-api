package com.example.cc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cc.dao.CreditCardDAO;
import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardDAO creditCardDAO;

	@Override
	public CreditCardAccountDetail addCreditCard(CreditCardAccount creditCardAccount) {
		return creditCardDAO.addCreditCard(creditCardAccount);
	}

	@Override
	public List<CreditCardAccountDetail> findAllCreditCardAccounts() {
		return creditCardDAO.findAllCreditCardAccounts();
	}

}
