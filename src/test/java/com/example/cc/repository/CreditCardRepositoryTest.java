package com.example.cc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cc.entity.CreditCardAccountEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Before
	public void setUp() {
		CreditCardAccountEntity creditCardAccountEntity = createCreditCardAccountEntity();
		testEntityManager.persist(creditCardAccountEntity);
	}

	@Test
	public void findByCardNumberTest() {
		String cardNumberToFind = "79927398713";
		CreditCardAccountEntity actual = creditCardRepository.findByCardNumber(cardNumberToFind).get(0);
		assertThat(actual.getCardNumber()).isEqualTo(cardNumberToFind);
	}

	@Test
	public void findAllTest() {
		Iterable<CreditCardAccountEntity> cards = creditCardRepository.findAll();
		long count = StreamSupport.stream(cards.spliterator(), false)
				.filter(card -> "79927398713".equals(card.getCardNumber())).count();
		assertEquals(count, 1);
	}

	private CreditCardAccountEntity createCreditCardAccountEntity() {
		CreditCardAccountEntity creditCardAccountEntity = new CreditCardAccountEntity();
		creditCardAccountEntity.setBalance(BigDecimal.valueOf(0.00));
		creditCardAccountEntity.setCardNumber("79927398713");
		creditCardAccountEntity.setCreditLimit(BigDecimal.valueOf(200.00));
		creditCardAccountEntity.setCurrency("GBP");
		creditCardAccountEntity.setUserName("user name");
		return creditCardAccountEntity;
	}
}
