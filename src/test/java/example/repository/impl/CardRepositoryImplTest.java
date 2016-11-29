package example.repository.impl;

import example.model.Card;
import example.repository.CardRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */

public class CardRepositoryImplTest {
    private static final String DECK_NAME = "deckName";
    private static final String ANOTHER_DECK_NAME = "anotherDeckName";
    private CardRepository cardRepository = CardRepositoryImpl.getInstance();

    @Before
    public void setUp() {
        Card card1 = new Card();
        card1.setDeckName(DECK_NAME);
        cardRepository.put(card1);

        Card card2 = new Card();
        card2.setDeckName(DECK_NAME);
        cardRepository.put(card2);
    }

    @After
    public void clean() {
        cardRepository.getAll().forEach(card -> cardRepository.remove(card.getId()));
    }

    @Test
    public void getAllFor() throws Exception {
        List<Card> cards = cardRepository.getAllFor(DECK_NAME);

        assertFalse(cards.isEmpty());
        assertEquals(2, cards.size());
    }

    @Test
    public void getAllForNotFound() {
        List<Card> cards = cardRepository.getAllFor(ANOTHER_DECK_NAME);

        assertTrue(cards.isEmpty());
    }

    @Test
    public void put() throws Exception {
        Card card = new Card();
        Card result = cardRepository.put(card);
        assertNotNull(result.getId());
    }
}