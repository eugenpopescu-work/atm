package com.eugenpopescu.projects.atm.loader;

import com.eugenpopescu.projects.atm.model.Card;
import com.eugenpopescu.projects.atm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    private final CardRepository cardRepository;

    @Autowired
    public DataLoader(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostConstruct
    private void loadCards() {
       List<Card> cards =  Arrays.asList(
                new Card("123121", "TestCard1", 1234),
                new Card("332244", "TestCard2", 0000),
                new Card("126532", "TestCard3", 1111)
        );
        cardRepository.saveAll(cards);

    }
}
