package com.example.cc.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.cc.domain.CreditCardAccount;
import com.example.cc.domain.CreditCardAccountDetail;
import com.example.cc.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CreditCardControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CreditCardService service;

	@Test
	public void findAllCreditCardAccountsTest() throws Exception {
		List<CreditCardAccountDetail> cardAccountList = Arrays.asList(createCreditCardAccountDetails());
		when(service.findAllCreditCardAccounts()).thenReturn(cardAccountList);

		mvc.perform(MockMvcRequestBuilders.get("/cards").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].userName", is(createCreditCardAccountDetails().getUserName())))
				.andExpect(jsonPath("$[0].cardNumber", is(createCreditCardAccountDetails().getCardNumber())))
				.andExpect(jsonPath("$[0].creditLimit", is(createCreditCardAccountDetails().getCreditLimit())))
				.andExpect(jsonPath("$[0].currency", is(createCreditCardAccountDetails().getCurrency())))
				.andExpect(jsonPath("$[0].balance", is(createCreditCardAccountDetails().getBalance())));
	}

	@Test
	public void addCreditCardAccountTest() throws Exception {
		CreditCardAccount creditCardAccount = createCreditCardAccount();
		when(service.addCreditCard(any())).thenReturn(createCreditCardAccountDetails());
		mvc.perform(MockMvcRequestBuilders.post("/cards").content(asJsonString(creditCardAccount))
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.userName", is(creditCardAccount.getUserName())))
				.andExpect(jsonPath("$.cardNumber", is(createCreditCardAccountDetails().getCardNumber())))
				.andExpect(jsonPath("$.creditLimit", is(createCreditCardAccountDetails().getCreditLimit())))
				.andExpect(jsonPath("$.currency", is(createCreditCardAccountDetails().getCurrency())))
				.andExpect(jsonPath("$.balance", is(createCreditCardAccountDetails().getBalance())));

	}

	private CreditCardAccount createCreditCardAccount() {
		CreditCardAccount creditCardAccount = new CreditCardAccount();
		creditCardAccount.setCardNumber("79927398713");
		creditCardAccount.setCreditLimit("200.00");
		creditCardAccount.setUserName("user name");

		return creditCardAccount;
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

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
