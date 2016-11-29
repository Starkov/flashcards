package example.service;

import example.model.Deck;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public interface DeckService {
    List<Deck> getAll();

    Deck get(String name);

    void put(Deck deck);
}
