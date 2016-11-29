package example.exception;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class DeckIsUnavailableException extends RuntimeException {
    public DeckIsUnavailableException(String message) {
        super(message);
    }
}
