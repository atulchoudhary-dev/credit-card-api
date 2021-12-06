package com.example.cc.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.cc.dao.impl.CreditCardDAOImpl;
import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.entity.CreditCardAccountEntity;
import com.example.cc.exception.CreditCardAlreadyExistsException;
import com.example.cc.repository.CreditCardRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardDAOImplTest {

	@InjectMocks
	private CreditCardDAOImpl creditCardDAO;

	@Mock
	private CreditCardRepository creditCardRepository;

	@Mock
	ModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(creditCardDAO, "openingBalance", 0);
		ReflectionTestUtils.setField(creditCardDAO, "supportedCurrency", "GBP");
	}

	@Test
	public void findAllCreditCardAccountsTest_Success() {
		CreditCardAccountDetail expectedCreditCardAccountDetail = createCreditCardAccountDetails();
		CreditCardAccountEntity entity = createCreditCardAccountEntity();
		Iterable<CreditCardAccountEntity> creditCardAccountEntityList = Arrays.asList(entity);
		doReturn(creditCardAccountEntityList).when(creditCardRepository).findAll();
		doReturn(expectedCreditCardAccountDetail).when(modelMapper).map(any(CreditCardAccountEntity.class),
				eq(CreditCardAccountDetail.class));
		List<CreditCardAccountDetail> actualCardAccountList = creditCardDAO.findAllCreditCardAccounts();
		assertThat(actualCardAccountList.get(0)).isEqualTo(expectedCreditCardAccountDetail);
	}

	@Test
	public void addCreditCardTest_Success() {
		CreditCardAccountEntity entity = createCreditCardAccountEntity();
		List<CreditCardAccountEntity> emptlyList = Collections.emptyList();
		CreditCardAccountEntity convertedEntity = convertedCreditCardAccountEntity();
		CreditCardAccountDetail convertedCreditCardAccountDetail = convertedCreditCardAccountDetails();
		doReturn(emptlyList).when(creditCardRepository).findByCardNumber(any(String.class));
		doReturn(convertedEntity).when(modelMapper).map(any(CreditCardAccount.class),
				eq(CreditCardAccountEntity.class));
		doReturn(entity).when(creditCardRepository).save(any(CreditCardAccountEntity.class));
		doReturn(convertedCreditCardAccountDetail).when(modelMapper).map(any(CreditCardAccountEntity.class),
				eq(CreditCardAccountDetail.class));
		CreditCardAccountDetail actual = creditCardDAO.addCreditCard(createCreditCardAccount());
		assertThat(actual).isEqualTo(convertedCreditCardAccountDetail);

	}

	@Test(expected = CreditCardAlreadyExistsException.class)
	public void addCreditCardTest_WhenCardAlreadyExists() {
		CreditCardAccountEntity entity = createCreditCardAccountEntity();
		Iterable<CreditCardAccountEntity> creditCardAccountEntityList = Arrays.asList(entity);
		doReturn(creditCardAccountEntityList).when(creditCardRepository).findByCardNumber(any(String.class));
		creditCardDAO.addCreditCard(createCreditCardAccount());

	}

	private CreditCardAccount createCreditCardAccount() {
		CreditCardAccount cardAccount = new CreditCardAccount();
		cardAccount.setCardNumber("79927398713");
		cardAccount.setCreditLimit("200.00");
		cardAccount.setUserName("user name");
		return cardAccount;
	}

	private CreditCardAccountDetail createCreditCardAccountDetails() {
		CreditCardAccountDetail cardAccountDetails = new CreditCardAccountDetail();
		cardAccountDetails.setBalance("200.00");
		cardAccountDetails.setCardNumber("79927398713");
		cardAccountDetails.setCreditLimit("200.00");
		cardAccountDetails.setCurrency("GBP");
		cardAccountDetails.setUserName("user name");
		return cardAccountDetails;
	}

	private CreditCardAccountEntity createCreditCardAccountEntity() {
		CreditCardAccountEntity creditCardAccountEntity = new CreditCardAccountEntity();
		creditCardAccountEntity.setBalance(BigDecimal.valueOf(200.00));
		creditCardAccountEntity.setCardNumber("79927398713");
		creditCardAccountEntity.setCreditLimit(BigDecimal.valueOf(200.00));
		creditCardAccountEntity.setCurrency("GBP");
		creditCardAccountEntity.setUserName("user name");
		return creditCardAccountEntity;
	}

	private CreditCardAccountEntity convertedCreditCardAccountEntity() {
		CreditCardAccountEntity creditCardAccountEntity = new CreditCardAccountEntity();
		creditCardAccountEntity.setCardNumber("79927398713");
		creditCardAccountEntity.setCreditLimit(BigDecimal.valueOf(200.00));
		creditCardAccountEntity.setUserName("user name");
		return creditCardAccountEntity;
	}

	private CreditCardAccountDetail convertedCreditCardAccountDetails() {
		CreditCardAccountDetail cardAccountDetails = new CreditCardAccountDetail();
		cardAccountDetails.setBalance("0.00");
		cardAccountDetails.setCardNumber("79927398713");
		cardAccountDetails.setCreditLimit("200.00");
		cardAccountDetails.setCurrency("GBP");
		cardAccountDetails.setUserName("user name");
		return cardAccountDetails;
	}

}
