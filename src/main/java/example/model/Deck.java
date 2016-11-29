package example.model;

import java.util.List;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class Deck {
    private String name;
    private List<Card> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deck deck = (Deck) o;

        return name.equals(deck.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
