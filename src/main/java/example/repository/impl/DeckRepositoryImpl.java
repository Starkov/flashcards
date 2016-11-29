package example.repository.impl;

import example.model.Deck;
import example.repository.DeckRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class DeckRepositoryImpl implements DeckRepository {
    public static final String DUPLICATE_OF_DECK_MSG = "Duplicate of deck: ";
    private static DeckRepositoryImpl instance;
    private TreeMap<String, Deck> storage = new TreeMap<>();

    public static DeckRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DeckRepositoryImpl();
        }
        return instance;
    }

    private DeckRepositoryImpl() {
    }

    @Override
    public Deck get(String name) {
        return storage.get(name);
    }

    @Override
    public void put(Deck deck) {
        if (storage.containsKey(deck.getName())){
            throw new RuntimeException(DUPLICATE_OF_DECK_MSG + deck.getName());
        }
        storage.put(deck.getName(), deck);
    }

    @Override
    public void remove(String name) {
        storage.remove(name);
    }

    @Override
    public List<Deck> getAll() {
        return new ArrayList<>(storage.values());
    }
}
