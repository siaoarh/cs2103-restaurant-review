package application.command;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import application.auth.AuthManager;
import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.parser.ArgumentParser;
import application.review.Review;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Class representing a command to unresolve a review.
 */
public class UnresolveReviewCommand extends Command {
    private final int index;

    /**
     * Constructor for UnresolveReviewCommand class.
     *
     * @param index the index of the review to unresolve
     */
    public UnresolveReviewCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unresolve a review in the list.
     *
     * @param reviews the list of reviews
     * @param storage the storage object
     * @param manager the authentication manager
     * @return a {@code CommandResult} object containing the result of the command execution
     * @throws InvalidArgumentException if the index is in the wrong format
     */
    @Override
    public CommandResult execute(
            ReviewList reviews,
            Storage storage,
            AuthManager manager
    ) throws InvalidArgumentException, IOException {
        Review review = reviews.markOutstanding(index);
        storage.saveReviews(reviews);

        return new CommandResult(
                String.format("%s\nmarked as outstanding!", review),
                isTerminatingCommand(),
                reviews
        );
    }
}
