package com.eugenpopescu.projects.atm;

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
class TestCardRepository {

	@Autowired
	private CardRepository cardRepository;

	private Card card;

	@BeforeEach
	public void setUp(){
		 card = new Card("123127", "TestCard91", 1234);
	}

	@AfterEach
	public void tearDown(){
		if(card != null && card.getCardId() > 0){
			cardRepository.deleteById(card.getCardId());
		}
	}

	@Test
	public void testSaveCard(){
		Card card = new Card("123127", "TestCard91", 1234);
		Card cardResult = cardRepository.save(card);
		assertAll("Test that card is saved",() ->{
			assertNotNull(cardResult);
			assertNotNull(cardResult.getCardId());
			assertEquals(card.getCardNumber(), cardResult.getCardNumber());
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
}
