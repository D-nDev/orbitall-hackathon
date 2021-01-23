package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import com.orbitallcorp.hack21.cards.repositories.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService implements ICardService {
    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save((card));
    }

    public Card getCard(Long id) {
        Optional<Card> cardResponse =  cardRepository.findById(id);
        Card card = null;
        try {
            if (cardResponse.isPresent()) {
                card = cardResponse.get();

            }
        }catch(Exception any) {
            throw new RuntimeException("No record found for given id: " + id);
        }
        return card;
    }

    public List<Card> findAll() {
        // O código abaixo é o sugerido pela comunidade Spring Boot:
        //List<> customers = new ArrayList<>();
        //customerRepository.findAll().forEach(customers :: add);

        // O código abaixo é a moda antiga, risos!
        List<Card> cards = new ArrayList<Card>();
        for (Card card : (List<Card>) cardRepository.findAll()) {
            cards.add(card);
        }

        // O código abaixo força o Iterable para List
        // return (List<Customer>) repository.findAll();

        return cards;
    }

    public void deleteCard(Long id ) {
        cardRepository.deleteById(id);
    }

}