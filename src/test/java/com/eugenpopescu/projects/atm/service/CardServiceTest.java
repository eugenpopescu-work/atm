package com.eugenpopescu.projects.atm.service;

import com.eugenpopescu.projects.atm.model.Card;
import com.eugenpopescu.projects.atm.repository.CardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Autowired
    CardRepository cardRepository;

    Card card;

    @BeforeEach
    public void setUp(){
        card = cardRepository.save( new Card("123129", "TestCard7", 1234));
    }

    @AfterEach
    public void tearDown(){
        if(card != null && card.getCardId() > 0){
            cardRepository.deleteById(card.getCardId());
        }
    }

    @Test
    public void testIncreaseBalance(){
        assertAll("Test that card balance is increased",() -> {
            assertNotNull(card.getCardBalance());
            assertNotNull(card.getCardId());
        });
        Card cardResult = cardService.increaseBalance(card.getCardNumber(), "15");
        assertAll("Test that card balance is increased",() ->{
            assertTrue(card.getCardBalance() < cardResult.getCardBalance());
            assertEquals(15, cardResult.getCardBalance());
        });
    }

    @Test
    public void testDecreaseBalance(){
        assertAll("Test that card balance is decreased",() -> {
            assertNotNull(card.getCardBalance());
            assertNotNull(card.getCardId());
        });
        final Card cardIncrease = cardService.increaseBalance(card.getCardNumber(), "15");
        final Card cardResult = cardService.decreaseBalance(cardIncrease.getCardNumber(), "10");
        assertAll("Test that card balance is increased",() ->{
            assertTrue(cardIncrease.getCardBalance() > cardResult.getCardBalance());
            assertEquals(5, cardResult.getCardBalance());
        });
    }

    @Test
    public void testFindByCardName(){
        List<Card> cardsDB = cardRepository.findByCardName(card.getCardName());
        assertAll("Test that card is saved",() ->{
            assertNotNull(cardsDB);
            assertFalse(cardsDB.isEmpty());
        });
    }

    @Test
    public void testDecreaseBalanceFailsNumberFormatException(){
        assertAll("Test that card balance is decreased",() -> {
            assertNotNull(card.getCardBalance());
            assertNotNull(card.getCardId());
        });

       assertThrows(NumberFormatException.class, () ->{
           final Card cardIncrease = cardService.increaseBalance(card.getCardNumber(), "abc");
       });
    }

    @Test
    public void testDecreaseBalanceFailsIllegalArgumentException(){
        assertAll("Test that card balance is decreased",() -> {
            assertNotNull(card.getCardBalance());
            assertNotNull(card.getCardId());
        });

        assertThrows(IllegalArgumentException.class, () ->{
            final Card cardIncrease = cardService.increaseBalance(card.getCardNumber(), null);
        });
    }

}