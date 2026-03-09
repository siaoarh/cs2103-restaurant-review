package application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for custom exception classes.
 */
public class ExceptionTest {
    /**
     * Tests the constructor and message of InvalidArgumentException.
     */
    @Test
    public void invalidArgumentException_message_success() {
        String message = "Invalid argument!";
        InvalidArgumentException exception = new InvalidArgumentException(message);
        assertEquals(message, exception.getMessage());
    }

    /**
     * Tests the constructor and message of MissingArgumentException.
     */
    @Test
    public void missingArgumentException_message_success() {
        String message = "Missing argument!";
        MissingArgumentException exception = new MissingArgumentException(message);
        assertEquals(message, exception.getMessage());
    }
}
