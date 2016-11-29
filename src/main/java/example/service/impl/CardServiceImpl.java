package example.service.impl;

import example.model.Card;
import example.repository.CardRepository;
import example.service.CardService;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllFor(String deckName) {
        return cardRepository.getAllFor(deckName);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.getAll();
    }

    @Override
    public Card put(Card card) {
        return cardRepository.put(card);
    }
}
