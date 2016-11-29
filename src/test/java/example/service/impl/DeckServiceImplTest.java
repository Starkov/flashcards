package example.service.impl;

import example.model.Card;
import example.model.Deck;
import example.repository.CardRepository;
import example.repository.DeckRepository;
import example.service.CardService;
import example.service.DeckService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeckServiceImplTest {
    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";
    private CardRepository cardRepository;
    private DeckRepository deckRepository;

    private DeckService deckService;

    @Before
    public void setUp() throws Exception {
        cardRepository = mock(CardRepository.class);
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckServiceImpl(deckRepository, cardRepository);
    }

    @Test
    public void getAll() throws Exception {
        Deck deck1 = new Deck();
        deck1.setName(NAME1);
        Deck deck2 = new Deck();
        deck2.setName(NAME2);
        when(deckRepository.getAll()).thenReturn(asList(deck1, deck2));
        Card card1 = new Card();
        card1.setDeckName(NAME1);
        when(cardRepository.getAllFor(NAME1)).thenReturn(asList(card1));
        Card card2 = new Card();
        card2.setDeckName(NAME2);
        when(cardRepository.getAllFor(NAME2)).thenReturn(asList(card2));

        List<Deck> result = deckService.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getCards());
        assertFalse(result.get(0).getCards().isEmpty());
        assertEquals(NAME1, result.get(0).getCards().get(0).getDeckName());
    }

    @Test
    public void get() throws Exception {
        Deck deck1 = new Deck();
        deck1.setName(NAME1);
        when(deckRepository.get(NAME1)).thenReturn(deck1);
        Card card1 = new Card();
        card1.setDeckName(NAME1);
        when(cardRepository.getAllFor(NAME1)).thenReturn(asList(card1));

        Deck result = deckService.get(NAME1);

        assertNotNull(result);
        assertEquals(NAME1, result.getName());
        assertNotNull(result.getCards());
        assertFalse(result.getCards().isEmpty());
        assertEquals(NAME1, result.getCards().get(0).getDeckName());
    }

    @Test
    public void put() throws Exception {
        Deck deck = new Deck();
        deck.setName(NAME1);
        deckService.put(deck);
        verify(deckRepository).put(deck);
    }

}