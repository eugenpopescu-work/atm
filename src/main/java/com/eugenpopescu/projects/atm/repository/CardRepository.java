package com.eugenpopescu.projects.atm.repository;

import org.springframework.data.repository.CrudRepository;

import com.eugenpopescu.projects.atm.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCardName(String cardName);
    Optional<Card> findByCardNumber(String cardNumber);
}
