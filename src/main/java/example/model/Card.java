package example.model;

import java.time.LocalDate;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class Card {
    private String id;
    private String front;
    private String back;
    private String deckName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }


    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!id.equals(card.id)) return false;
        if (!front.equals(card.front)) return false;
        if (!back.equals(card.back)) return false;
        return deckName.equals(card.deckName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + front.hashCode();
        result = 31 * result + back.hashCode();
        result = 31 * result + deckName.hashCode();
        return result;
    }
}
