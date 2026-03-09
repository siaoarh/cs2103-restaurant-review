package application.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ExitCommand class.
 */
public class ExitCommandTest {
    private ExitCommand command;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        command = new ExitCommand();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        command = null;
    }

    /**
     * Tests if the exit command is recognised as a terminating command.
     */
    @Test
    public void exitCommand_isTerminating_success() {
        assertTrue(command.isTerminatingCommand());
    }

    /**
     * Tests the execution of the exit command.
     */
    @Test
    public void exitCommand_execute_success() {
        String output = command.execute(null, null);
        assertTrue(output.contains("Goodbye!"));
    }
}
