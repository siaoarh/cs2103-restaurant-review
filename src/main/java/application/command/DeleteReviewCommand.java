package application.command;

import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.parser.Utility;
import application.review.Review;
import application.review.ReviewList;
import application.storage.Storage;

import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to delete a review.
 */
public class DeleteReviewCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for DeleteReviewCommand class.
     * @param commandArgs the arguments of the command
     */
    public DeleteReviewCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the command to delete a review from the list.
     * @param reviewList the list of reviews
     * @param storage the storage object
     * @return a string representation of the command result
     * @throws MissingArgumentException if the index is missing
     * @throws InvalidArgumentException if the index is in the wrong format
     */
    @Override
    public String execute(ReviewList reviewList, Storage storage)
            throws MissingArgumentException, InvalidArgumentException {
        int index = Utility.toInt(commandArgs.get("/default"));
        Review review = reviewList.removeReview(index);

        return String.format("%s\ndeleted!", review);
    }
}
