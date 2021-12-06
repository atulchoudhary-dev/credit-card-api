package com.example.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cc.entity.CreditCardAccountEntity;

/**
 * Crud repository for credit card account
 *
 */
@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardAccountEntity, Long> {

	List<CreditCardAccountEntity> findByCardNumber(String cardNumber);

}