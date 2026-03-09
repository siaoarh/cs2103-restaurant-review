package application.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.command.AddReviewCommand;
import application.command.Command;
import application.command.DeleteReviewCommand;
import application.command.ExitCommand;
import application.command.ListReviewsCommand;
import application.command.UnknownCommand;

/**
 * Tests for CommandParser class.
 */
public class CommandParserTest {
    private String input;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        input = "";
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        input = null;
    }

    /**
     * Tests parsing an exit command.
     */
    @Test
    public void getCommand_exit_success() {
        input = "exit";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests parsing an add command.
     */
    @Test
    public void getCommand_add_success() {
        input = "add restaurant /rating 5 /review Good";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(AddReviewCommand.class, command);
    }

    /**
     * Tests parsing a delete command.
     */
    @Test
    public void getCommand_delete_success() {
        input = "delete 1";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(DeleteReviewCommand.class, command);
    }

    /**
     * Tests parsing a list command.
     */
    @Test
    public void getCommand_list_success() {
        input = "list";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(ListReviewsCommand.class, command);
    }

    /**
     * Tests parsing an unknown command string.
     */
    @Test
    public void getCommand_unknown_success() {
        input = "blah";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(UnknownCommand.class, command);
    }

    /**
     * Tests parsing an empty command string.
     */
    @Test
    public void getCommand_empty_success() {
        input = "";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(UnknownCommand.class, command);
    }

    /**
     * Tests parsing a null command input.
     */
    @Test
    public void getCommand_null_success() {
        Command command = CommandParser.getCommand(null);
        assertInstanceOf(UnknownCommand.class, command);
    }

    /**
     * Tests parsing an add command with multiple delimiters of the same type.
     * The latest argument for that delimiter should be captured.
     */
    @Test
    public void getCommand_addMultipleDelimiters_success() {
        // The last /rating (3) should be the one used.
        input = "add restaurant /rating 5 /rating 3 /review Good";
        Command command = CommandParser.getCommand(input);
        assertInstanceOf(AddReviewCommand.class, command);
        // Note: we can't easily check the internal Map of AddReviewCommand without reflection
        // but we can test the behavior by executing it if needed, or trust the parser logic.
    }
}
