package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card savedCard = cardService.save((card));

        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Card getCard(@PathVariable Long id) {
        Card cardResponse = (Card) cardService.getCard(id);
        return cardResponse;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "Card has been deleted successfully";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateCard(@RequestBody Card card, @PathVariable long id) {

        Optional<Card> cardOptional = cardRepository.findById(id);

        if (!cardOptional.isPresent())
            return "Card has been updated successfully";

        card.setId(id);

        cardRepository.save(card);

        return "Card has been updated successfully";
    }
}