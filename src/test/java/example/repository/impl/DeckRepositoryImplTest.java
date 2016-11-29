package example.repository.impl;

import example.model.Deck;
import example.repository.DeckRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class DeckRepositoryImplTest {
    private static final String DECK_NAME = "deckName";
    private static final String ANOTHER_DECK_NAME = "anotherDeckName";
    private DeckRepository deckRepository = DeckRepositoryImpl.getInstance();

    @Before
    public void setUp() {
        Deck deck = new Deck();
        deck.setName(DECK_NAME);
        deckRepository.put(deck);
    }

    @After
    public void clean() {
        deckRepository.getAll().forEach(deck -> deckRepository.remove(deck.getName()));
    }

    @Test
    public void get() throws Exception {
        Deck result = deckRepository.get(DECK_NAME);

        assertNotNull(result);
        assertEquals(DECK_NAME, result.getName());
    }

    @Test(expected = RuntimeException.class)
    public void putDuplicate() throws Exception {
        Deck deck = new Deck();
        deck.setName(DECK_NAME);

        deckRepository.put(deck);
    }

    @Test
    public void removeNonexistentDeck(){
        deckRepository.remove(ANOTHER_DECK_NAME);
        assert true;
    }

}