package example.util;

import example.exception.IllegalCardFormatException;
import example.model.Card;
import example.model.Deck;
import example.service.CardService;
import example.service.DeckService;

import java.nio.file.Path;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class ModelLoader {
    private static final String INCORRECT_FORMAT_OF_CARD_MSG = "Incorrect format of card: ";
    private DeckService deckService;
    private CardService cardService;

    public ModelLoader(DeckService deckService, CardService cardService) {
        this.deckService = deckService;
        this.cardService = cardService;
    }

    public void loadCard(String cardLine, Deck deck) {
        Card card = extractCard(cardLine);
        card.setDeckName(deck.getName());
        cardService.put(card);
    }

    public Deck loadDeck(String name) {
        Deck deck = new Deck();
        deck.setName(name);
        deckService.put(deck);
        return deck;
    }

    private Card extractCard(String cardLine) {
        String[] resultSet = cardLine.split(";");
        if (resultSet.length != 2) {
            throw new IllegalCardFormatException(INCORRECT_FORMAT_OF_CARD_MSG + resultSet[0]);
        }
        Card card = new Card();
        card.setFront(resultSet[0]);
        card.setBack(resultSet[1]);
        return card;
    }
}
