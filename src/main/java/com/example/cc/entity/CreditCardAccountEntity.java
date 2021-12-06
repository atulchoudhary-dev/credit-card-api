package com.example.cc.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Entity for persisting credit card account in DB
 *
 */
@Data
@Entity
@Table(name = "CREDIT_CARD_ACCOUNT")
public class CreditCardAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(max = 15)
	@Column(name = "user_name", length = 15)
	private String userName;

	@Size(max = 20)
	@Column(name = "card_number", length = 20)
	private String cardNumber;

	@Size(max = 5)
	@Column(name = "currency", length = 5)
	private String currency;

	@Digits(integer=15, fraction=2)
	@Column(name = "balance")
	private BigDecimal balance;

	@Digits(integer=15, fraction=2)
	@Column(name = "credit_limit")
	private BigDecimal creditLimit;

}
