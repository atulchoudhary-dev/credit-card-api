package com.example.cc.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.cc.dao.CreditCardDAO;
import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.entity.CreditCardAccountEntity;
import com.example.cc.exception.CreditCardAlreadyExistsException;
import com.example.cc.repository.CreditCardRepository;

@Repository
public class CreditCardDAOImpl implements CreditCardDAO {

	@Value("${cc.openning.balance}")
	private long openingBalance;

	@Value("${cc.supported.currency}")
	private String supportedCurrency;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CreditCardAccountDetail addCreditCard(CreditCardAccount creditCardAccount) {
		List<CreditCardAccountEntity> cardDetails = creditCardRepository
				.findByCardNumber(creditCardAccount.getCardNumber());
		cardDetails.stream().findFirst().ifPresent((entity) -> {
			throw new CreditCardAlreadyExistsException("Credit card already Exists");
		});
		CreditCardAccountEntity entity = modelMapper.map(creditCardAccount, CreditCardAccountEntity.class);
		entity.setBalance(BigDecimal.valueOf(openingBalance, 2));
		entity.setCurrency(supportedCurrency);
		entity = creditCardRepository.save(entity);
		return modelMapper.map(entity, CreditCardAccountDetail.class);
	}

	@Override
	public List<CreditCardAccountDetail> findAllCreditCardAccounts() {
		Iterable<CreditCardAccountEntity> results = creditCardRepository.findAll();
		List<CreditCardAccountDetail> list = StreamSupport.stream(results.spliterator(), false)
				.map(entity -> modelMapper.map(entity, CreditCardAccountDetail.class)).collect(Collectors.toList());
		return list;
	}

}
