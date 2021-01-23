package com.orbitallcorp.hack21.cards.repositories;

import com.orbitallcorp.hack21.cards.domains.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ICardService {
    public Card getCard(Long id);

    public void deleteCard(Long id);
}

