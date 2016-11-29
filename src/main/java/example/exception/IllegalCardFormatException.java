package example.exception;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class IllegalCardFormatException extends RuntimeException {
    public IllegalCardFormatException(String message) {
        super(message);
    }
}
