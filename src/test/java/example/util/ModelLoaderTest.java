package example.util;

import example.exception.IllegalCardFormatException;
import example.model.Card;
import example.model.Deck;
import example.repository.CardRepository;
import example.repository.DeckRepository;
import example.service.CardService;
import example.service.DeckService;
import example.service.impl.DeckServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelLoaderTest {

    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";
    private static final String CARD_LINE = "world;мир";
    private static final String WRONG_CARD_LINE = "wrong card line";
    private CardService cardService;
    private DeckService deckService;

    private ModelLoader modelLoader;

    @Before
    public void setUp() throws Exception {
        cardService = mock(CardService.class);
        deckService = mock(DeckService.class);
        modelLoader = new ModelLoader(deckService, cardService);
    }

    @Test
    public void loadCard() throws Exception {
        Deck deck = new Deck();
        deck.setName(NAME1);

        modelLoader.loadCard(CARD_LINE, deck);

        ArgumentCaptor<Card> cardArg = ArgumentCaptor.forClass(Card.class);
        verify(cardService).put(cardArg.capture());

        assertEquals("world",cardArg.getValue().getFront());
        assertEquals("мир",cardArg.getValue().getBack());
        assertEquals(NAME1,cardArg.getValue().getDeckName());

    }

    @Test(expected = IllegalCardFormatException.class)
    public void loadCardException() throws Exception {
        modelLoader.loadCard(WRONG_CARD_LINE,new Deck());
    }

    @Test
    public void loadDeck() throws Exception {
        Deck deck = new Deck();
        deck.setName(NAME1);

        modelLoader.loadDeck(NAME1);

        ArgumentCaptor<Deck> deckArg = ArgumentCaptor.forClass(Deck.class);
        verify(deckService).put(deckArg.capture());
        assertEquals(NAME1,deckArg.getValue().getName());
    }

}