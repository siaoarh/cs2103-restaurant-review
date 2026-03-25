package application;

import java.io.IOException;

import application.command.Command;
import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.parser.CommandParser;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Represents the core MealMeter application logic.
 *
 * <p>This class acts as the bridge between the user interface
 * and the command-processing logic.</p>
 */
public class MealMeter {
    private final Storage storage;
    private final ReviewList reviewList;

    /**
     * Constructs a MealMeter application and loads stored reviews.
     */
    public MealMeter() {
        this.storage = new Storage();
        this.reviewList = loadReviews();
    }

    /**
     * Returns the welcome message shown at application startup.
     *
     * @return the welcome message
     */
    public String getWelcomeMessage() {
        return "Welcome to MealMeter.\n"
                + "Enter a command to manage customer reviews.\n"
                + "Type 'exit' to leave the application.";
    }

    /**
     * Returns the response for a user input command.
     *
     * @param userInput the raw user input
     * @return the resulting output message
     */
    public String getResponse(String userInput) {
        try {
            Command command = CommandParser.getCommand(userInput);
            return command.execute(reviewList, storage);
        } catch (InvalidArgumentException | MissingArgumentException | IOException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Returns whether the given input should terminate the application.
     *
     * @param userInput the raw user input
     * @return true if the command is terminating, false otherwise
     */
    public boolean isExit(String userInput) {
        try {
            Command command = CommandParser.getCommand(userInput);
            return command.isTerminatingCommand();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            return false;
        }
    }

    private ReviewList loadReviews() {
        try {
            return storage.loadReviews();
        } catch (IOException e) {
            System.out.println("Warning: Failed to load reviews from storage. Starting with an empty list.");
            return new ReviewList();
        }
    }
}
