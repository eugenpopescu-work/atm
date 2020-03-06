package com.eugenpopescu.projects.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eugenpopescu.projects.atm.model.Card;
import com.eugenpopescu.projects.atm.service.CardService;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{cardName}")
    public List<Card> findByCardName(@PathVariable String cardName) {

        return cardService.findByCardName(cardName);
    }

    @GetMapping("/check/{cardNumber}")
    public double checkBalance(@PathVariable String cardNumber) {

        return cardService.checkBalance(cardNumber);
    }

    @PutMapping("/deposit/{cardNumber}")
    public Card increaseBalance(@PathVariable String cardNumber, @RequestBody Object amount) {
        String amountStr = (String)((LinkedHashMap) amount).get("amount");
        return cardService.increaseBalance(cardNumber, amountStr);
    }

    @PutMapping("/withdraw/{cardNumber}")
    public Card decreaseBalance(@PathVariable String cardNumber, @RequestBody Object amount) {
        String amountStr = (String)((LinkedHashMap) amount).get("amount");
        return cardService.decreaseBalance(cardNumber, amountStr);
    }
}
