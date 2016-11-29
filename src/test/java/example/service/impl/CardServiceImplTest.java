package example.service.impl;

import example.model.Card;
import example.repository.CardRepository;
import example.repository.DeckRepository;
import example.service.CardService;
import example.service.DeckService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {
    private static final String DECK_NAME = "deckName";

    private CardRepository cardRepository;

    private CardService cardService;

    @Before
    public void setUp() {
        cardRepository = mock(CardRepository.class);
        cardService = new CardServiceImpl(cardRepository);
    }

    @Test
    public void getAllFor() throws Exception {
        when(cardRepository.getAllFor(DECK_NAME)).thenReturn(Collections.singletonList(new Card()));
        List<Card> result = cardService.getAllFor(DECK_NAME);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void getAll() throws Exception {
        when(cardRepository.getAll()).thenReturn(asList(new Card()));
        List<Card> result = cardService.getAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void put() throws Exception {
        Card card = new Card();
        card.setId("123");
        cardService.put(card);
        verify(cardRepository).put(card);
    }

}