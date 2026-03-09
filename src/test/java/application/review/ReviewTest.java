package application.review;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Review class.
 */
public class ReviewTest {
    private Review review;
    private Rating rating;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        rating = new Rating(4.0f);
        review = new Review("John Doe", "Good food!", rating);
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        review = null;
        rating = null;
    }

    /**
     * Tests the constructor and getters of the Review class.
     */
    @Test
    public void review_constructorAndGetters_success() {
        assertEquals("John Doe", review.getUser());
        assertEquals("Good food!", review.getReview());
        assertEquals(rating, review.getRating());
    }

    /**
     * Tests the string representation of a review.
     */
    @Test
    public void review_toString_success() {
        String expected = "Review by: John Doe\nRating: 4.0\nReview: Good food!";
        assertEquals(expected, review.toString());
    }
}
