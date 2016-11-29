package example.repository.impl;

import example.model.Card;
import example.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class CardRepositoryImpl implements CardRepository {
    private static CardRepositoryImpl instance;
    private TreeMap<String, Card> storage = new TreeMap<>();

    public static CardRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CardRepositoryImpl();
        }
        return instance;
    }

    private CardRepositoryImpl() {
    }

    @Override
    public List<Card> getAll() {
        return new ArrayList<Card>(storage.values());
    }

    @Override
    public List<Card> getAllFor(String deckName) {
        return storage.values().stream().filter(card -> deckName.equals(card.getDeckName())).collect(Collectors.toList());
    }

    @Override
    public Card put(Card card) {
        card.setId(UUID.randomUUID().toString());
        storage.put(card.getId(), card);
        return card;
    }

    @Override
    public void remove(String id) {
        storage.remove(id);
    }
}
