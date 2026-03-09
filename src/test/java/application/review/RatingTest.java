package application.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Rating class.
 */
public class RatingTest {
    private Rating rating;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        rating = new Rating(4.5f);
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        rating = null;
    }

    /**
     * Tests the constructor and getter for the rating value.
     */
    @Test
    public void rating_constructorAndGetter_success() {
        assertEquals(4.5f, rating.getRatingValue());
    }

    /**
     * Tests the static validation method for ratings.
     */
    @Test
    public void rating_isValidRating_success() {
        assertTrue(Rating.isValidRating(0.0f));
        assertTrue(Rating.isValidRating(2.5f));
        assertTrue(Rating.isValidRating(5.0f));
        assertFalse(Rating.isValidRating(-0.1f));
        assertFalse(Rating.isValidRating(5.1f));
    }

    /**
     * Tests the string representation of a rating.
     */
    @Test
    public void rating_toString_success() {
        assertEquals("Rating: 4.5", rating.toString());
    }
}
