package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import application.command.CommandResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.auth.AuthManager;
import application.storage.Storage;

/**
 * Tests for MealMeterController authentication and command gating behavior.
 */
public class MealMeterTest {
    private static final String OWNER_PASSWORD = "secret";

    private Path tempDirectory;
    private MealMeterController mealMeterController;

    @BeforeEach
    public void setUp() throws IOException {
        tempDirectory = Files.createTempDirectory("mealmeter-auth-test-");
        Path storagePath = tempDirectory.resolve("data").resolve("reviews.txt");
        Storage storage = new Storage(storagePath);

        mealMeterController = new MealMeterController(storage, new AuthManager(OWNER_PASSWORD));
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (tempDirectory == null || !Files.exists(tempDirectory)) {
            return;
        }

        Files.walk(tempDirectory)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException ignored) {
                        fail("Failed to delete temp path: " + path, ignored);
                    }
                });
    }

    @Test
    public void handleInput_patronCommandWithoutLogin_allowed() {
        CommandResult result = mealMeterController.handleInput("review great /food 4 /clean 4 /service 4");

        assertTrue(result.output().contains("Added review to list:"));
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_ownerCommandWithoutLogin_denied() {
        CommandResult result = mealMeterController.handleInput("list");

        assertEquals("Access denied. Please log in as the owner to use this command.", result.output());
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_unknownCommandWithoutLogin_unknownHandledNormally() {
        CommandResult result = mealMeterController.handleInput("somethinginvalid");

        assertEquals("I'm sorry, I don't understand that command.", result.output());
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_loginInvalidFormat_returnsUsage() {
        CommandResult result = mealMeterController.handleInput("login");

        assertEquals("Please enter a valid password.", result.output());
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_loginWrongPassword_returnsFailure() {
        CommandResult result = mealMeterController.handleInput("login wrong");

        assertEquals("Incorrect password!", result.output());
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_loginSuccessThenAlreadyLoggedIn() {
        CommandResult firstResult = mealMeterController.handleInput("LoGiN secret");
        CommandResult secondResult = mealMeterController.handleInput("login secret");

        assertEquals("Successfully logged in!", firstResult.output());
        assertFalse(firstResult.shouldTerminate());

        assertEquals("You are already logged in!", secondResult.output());
        assertFalse(secondResult.shouldTerminate());
    }

    @Test
    public void handleInput_ownerCommandAfterLogin_allowed() {
        mealMeterController.handleInput("login secret");
        CommandResult result = mealMeterController.handleInput("list");

        assertEquals("Review list is empty.", result.output());
        assertFalse(result.shouldTerminate());
    }

    @Test
    public void handleInput_exit_stillTerminates() {
        mealMeterController.handleInput("login secret");
        CommandResult result = mealMeterController.handleInput("exit");

        assertEquals("Goodbye!", result.output());
        assertTrue(result.shouldTerminate());
    }
}
