package com.example.cc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.cc.dao.CreditCardDAO;
import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.service.impl.CreditCardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceImplTest {

	@Mock
	private CreditCardDAO creditCardDAO;

	@InjectMocks
	private CreditCardServiceImpl creditCardService;

	@Test
	public void findAllCreditCardAccountsTest() {
		List<CreditCardAccountDetail> expectedCardAccountList = Arrays.asList(createCreditCardAccountDetails());
		doReturn(expectedCardAccountList).when(creditCardDAO).findAllCreditCardAccounts();
		List<CreditCardAccountDetail> actualCardAccountList = creditCardService.findAllCreditCardAccounts();
		assertThat(actualCardAccountList).isEqualTo(expectedCardAccountList);
	}

	@Test
	public void addCreditCardTest() {
		CreditCardAccountDetail expectedCreditCardAccountDetail = createCreditCardAccountDetails();
		CreditCardAccount request = createCreditCardAccount();
		doReturn(expectedCreditCardAccountDetail).when(creditCardDAO).addCreditCard(request);
		CreditCardAccountDetail actualCreditCardAccountDetail = creditCardService.addCreditCard(request);
		assertThat(actualCreditCardAccountDetail).isEqualTo(expectedCreditCardAccountDetail);
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
}
