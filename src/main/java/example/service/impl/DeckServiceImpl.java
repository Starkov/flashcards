package example.service.impl;

import example.model.Card;
import example.model.Deck;
import example.repository.CardRepository;
import example.repository.DeckRepository;
import example.service.DeckService;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class DeckServiceImpl implements DeckService {
    private DeckRepository deckRepository;
    private CardRepository cardRepository;

    public DeckServiceImpl(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Deck> getAll() {
        List<Deck> decks = deckRepository.getAll();
        decks.forEach(deck -> deck.setCards(cardRepository.getAllFor(deck.getName())));
        return decks;
    }

    @Override
    public Deck get(String name) {
        Deck deck = deckRepository.get(name);
        if(deck == null){
            return deck;
        }
        List<Card> cards = cardRepository.getAllFor(name);
        deck.setCards(cards);
        return deck;
    }

    @Override
    public void put(Deck deck) {
        deckRepository.put(deck);
    }
}
