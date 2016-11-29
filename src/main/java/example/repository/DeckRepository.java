package example.repository;

import example.model.Deck;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public interface DeckRepository {
    Deck get(String name);

    void put(Deck deck);

    void remove(String name);

    List<Deck> getAll();
}
