package com.eugenpopescu.projects.atm.service;

import com.eugenpopescu.projects.atm.exception.BalanceExceededException;
import com.eugenpopescu.projects.atm.exception.CardNotFoundException;
import com.eugenpopescu.projects.atm.model.Card;
import com.eugenpopescu.projects.atm.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findByCardName(String cardName) {
        logger.info(("Card " + cardName + " found!"));
        return cardRepository.findByCardName(cardName);
    }

    public double checkBalance(String cardNumber) {
        if(cardNumber == null || cardNumber.trim().equalsIgnoreCase("")){
            throw new CardNotFoundException();
        }

        Card cardDB = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        logger.info("Balance for card with number" + cardNumber + " is: " + cardDB.getCardBalance());

        return cardDB.getCardBalance();
    }

    public Card decreaseBalance(String cardNumber, String amountStr) {
        if(amountStr == null || amountStr.trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }
        Long amount = Long.parseLong(amountStr);
        if(cardNumber == null || cardNumber.trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }

       Card cardDB = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        if(cardDB.getCardBalance() < amount){
            throw new BalanceExceededException();
        }

        cardDB.setCardBalance(cardDB.getCardBalance() - amount);
        logger.info("Amount " + amount + " withdrawn!");

        return cardRepository.save(cardDB);
    }

    public Card increaseBalance(String cardNumber, String amountStr){
        if(amountStr == null || amountStr.trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }
        Long amount = Long.parseLong(amountStr);
        if(cardNumber == null || cardNumber.trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }

        Card cardDB = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        cardDB.setCardBalance(cardDB.getCardBalance() + amount);
        logger.info("Amount " + amount + " withdrawn!");

        return cardRepository.save(cardDB);
    }
}
