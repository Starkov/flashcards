package example.repository;

import example.model.Card;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public interface CardRepository {
    List<Card> getAll();
    List<Card> getAllFor(String deckName);
    Card put(Card card);
    void remove(String id);
}
