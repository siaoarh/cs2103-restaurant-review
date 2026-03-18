package application.command;

import java.util.Map;
import java.util.Set;

import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.parser.ArgumentParser;
import application.review.Rating;
import application.review.Review;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Class representing a command to add a review.
 */
public class AddReviewCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default", "/food", "/clean", "/service", "/tag");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for AddReviewCommand class.
     *
     * @param commandArgs the arguments of the command
     */
    public AddReviewCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the command to add a review to the list.
     *
     * @param reviewList the list of reviews
     * @param storage the storage object
     * @return a string representation of the command result
     * @throws MissingArgumentException if any argument is missing
     * @throws InvalidArgumentException if any argument is in the wrong format
     */
    @Override
    public String execute(ReviewList reviewList, Storage storage)
            throws MissingArgumentException, InvalidArgumentException {
        String reviewBody = commandArgs.get("/default");
        String foodScoreAsString = commandArgs.get("/food");
        String cleanlinessScoreAsString = commandArgs.get("/clean");
        String serviceScoreAsString = commandArgs.get("/service");
        String tagsAsString = commandArgs.get("/tag");

        double foodScore = ArgumentParser.toDouble(foodScoreAsString);
        double cleanlinessScore = ArgumentParser.toDouble(cleanlinessScoreAsString);
        double serviceScore = ArgumentParser.toDouble(serviceScoreAsString);

        Rating rating = new Rating(foodScore, cleanlinessScore, serviceScore);

        Review review = new Review(user, reviewBody, rating);
        reviewList.addReview(review);

        return String.format("Added review to list:\n%s", review);
    }
}
