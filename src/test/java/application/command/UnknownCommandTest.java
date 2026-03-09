package application.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for UnknownCommand class.
 */
public class UnknownCommandTest {
    private UnknownCommand command;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        command = new UnknownCommand();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        command = null;
    }

    /**
     * Tests the execution of the unknown command.
     */
    @Test
    public void unknownCommand_execute_success() {
        String output = command.execute(null, null);
        assertEquals("I'm sorry, I don't understand that command.", output);
    }
}
