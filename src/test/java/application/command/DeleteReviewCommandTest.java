package application.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.review.Rating;
import application.review.Review;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Tests for DeleteReviewCommand class.
 */
public class DeleteReviewCommandTest {
    private ReviewList reviewList;
    private Storage storage;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        reviewList = new ReviewList();
        storage = new Storage();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        reviewList = null;
        storage = null;
    }

    /**
     * Tests deleting a review with a valid index.
     * @throws Exception if an error occurs during execution
     */
    @Test
    public void deleteReviewCommand_validIndex_success() throws Exception {
        // Add a review first
        reviewList.addReview(new Review("User", "Great", new Rating(5)));

        Map<String, String> args = Map.of("/default", "1");
        DeleteReviewCommand command = new DeleteReviewCommand(args);
        String output = command.execute(reviewList, storage);

        assertTrue(output.contains("deleted!"));
        assertEquals("No reviews yet!", reviewList.toString());
    }

    /**
     * Tests deleting a review with an invalid (non-numeric) index.
     */
    @Test
    public void deleteReviewCommand_invalidIndex_throwsException() {
        Map<String, String> args = Map.of("/default", "abc");
        DeleteReviewCommand command = new DeleteReviewCommand(args);
        assertThrows(InvalidArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests deleting a review with an index that is out of bounds.
     */
    @Test
    public void deleteReviewCommand_indexOutOfBounds_throwsException() {
        Map<String, String> args = Map.of("/default", "1");
        DeleteReviewCommand command = new DeleteReviewCommand(args);
        // List is empty, so index 1 is out of bounds
        assertThrows(InvalidArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests deleting a review with a missing index argument.
     */
    @Test
    public void deleteReviewCommand_missingIndex_throwsException() {
        Map<String, String> args = Map.of();
        DeleteReviewCommand command = new DeleteReviewCommand(args);
        assertThrows(MissingArgumentException.class, () -> command.execute(reviewList, storage));
    }
}
