package application.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.review.Rating;
import application.review.Review;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Tests for ListReviewsCommand class.
 */
public class ListReviewsCommandTest {
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
     * Tests listing reviews with both empty and non-empty lists.
     */
    @Test
    public void listReviewsCommand_success() {
        ListReviewsCommand command = new ListReviewsCommand();
        String output = command.execute(reviewList, storage);
        assertEquals("No reviews yet!", output);

        reviewList.addReview(new Review("User", "Great", new Rating(5)));
        output = command.execute(reviewList, storage);
        assertTrue(output.contains("User"));
    }
}
