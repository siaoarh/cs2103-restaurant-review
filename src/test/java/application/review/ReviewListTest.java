package application.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.exception.InvalidArgumentException;

/**
 * Tests for ReviewList class.
 */
public class ReviewListTest {
    private ReviewList reviewList;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        reviewList = new ReviewList();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        reviewList = null;
    }

    /**
     * Tests the constructor for ReviewList class with a list of reviews.
     */
    @Test
    public void reviewListConstructor_listInput_success() {
        Review review1 = new Review("User1", "Great", new Rating(5));
        Review review2 = new Review("User2", "Bad", new Rating(1));
        List<Review> reviews = List.of(review1, review2);

        ReviewList list = new ReviewList(reviews);
        String output = list.toString();

        assertTrue(output.contains("User1"));
        assertTrue(output.contains("User2"));
        assertTrue(output.contains("Rating: 5.0"));
        assertTrue(output.contains("Rating: 1.0"));
    }

    /**
     * Tests adding a valid review to the list.
     */
    @Test
    public void addReview_validReview_success() {
        Review review = new Review("User", "Nice", new Rating(5));
        reviewList.addReview(review);
        assertEquals("1.\n" + review, reviewList.toString());
    }

    /**
     * Tests removing a review with a valid index.
     * @throws Exception if an unexpected error occurs
     */
    @Test
    public void removeReview_validIndex_success() throws Exception {
        Review review = new Review("User", "Nice", new Rating(5));
        reviewList.addReview(review);
        Review removed = reviewList.removeReview(1);
        assertEquals(review, removed);
        assertEquals("No reviews yet!", reviewList.toString());
    }

    /**
     * Tests removing a review with an invalid index on an empty list.
     */
    @Test
    public void removeReview_invalidIndex_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> reviewList.removeReview(1));
    }

    /**
     * Tests removing a review with a negative index.
     */
    @Test
    public void removeReview_negativeIndex_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> reviewList.removeReview(-1));
    }

    /**
     * Tests removing a review with an index of zero.
     */
    @Test
    public void removeReview_zeroIndex_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> reviewList.removeReview(0));
    }

    /**
     * Tests the string representation of an empty review list.
     */
    @Test
    public void toString_emptyList_success() {
        assertEquals("No reviews yet!", reviewList.toString());
    }
}
