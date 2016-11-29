package example.service;

import example.model.Card;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public interface CardService {
    List<Card> getAllFor(String deckName);
    List<Card> getAll();
    Card put(Card card);
}
