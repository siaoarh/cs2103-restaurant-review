package application.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Tests for AddReviewCommand class.
 */
public class AddReviewCommandTest {
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
     * Tests adding a review with valid arguments.
     * @throws Exception if an error occurs during execution
     */
    @Test
    public void addReviewCommand_validArgs_success() throws Exception {
        Map<String, String> args = Map.of(
            "/default", "User",
            "/rating", "5",
            "/review", "Great"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        String output = command.execute(reviewList, storage);

        assertTrue(output.contains("Added review to list:"));
        assertTrue(output.contains("User"));
        assertTrue(output.contains("Rating: 5.0"));
        assertTrue(output.contains("Great"));
        assertTrue(reviewList.toString().contains("User"));
    }

    /**
     * Tests adding a review with a missing user.
     */
    @Test
    public void addReviewCommand_missingUser_throwsException() {
        Map<String, String> args = Map.of(
            "/rating", "5",
            "/review", "Great"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        assertThrows(MissingArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests adding a review with a missing review body.
     */
    @Test
    public void addReviewCommand_missingReview_throwsException() {
        Map<String, String> args = Map.of(
            "/default", "User",
            "/rating", "5"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        assertThrows(MissingArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests adding a review with a missing rating.
     */
    @Test
    public void addReviewCommand_missingRating_throwsException() {
        Map<String, String> args = Map.of(
                "/default", "User",
                "/review", "a"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        assertThrows(MissingArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests adding a review with an invalid rating format.
     */
    @Test
    public void addReviewCommand_invalidRating_throwsException() {
        Map<String, String> args = Map.of(
            "/default", "User",
            "/rating", "invalid",
            "/review", "Great"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        assertThrows(InvalidArgumentException.class, () -> command.execute(reviewList, storage));
    }

    /**
     * Tests adding a review with a rating out of the valid range.
     */
    @Test
    public void addReviewCommand_ratingOutOfRange_throwsException() {
        Map<String, String> args = Map.of(
            "/default", "User",
            "/rating", "6",
            "/review", "Great"
        );
        AddReviewCommand command = new AddReviewCommand(args);
        assertThrows(InvalidArgumentException.class, () -> command.execute(reviewList, storage));
    }
}
