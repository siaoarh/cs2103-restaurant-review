package application.review;

import java.util.ArrayList;
import java.util.List;

import application.exception.InvalidArgumentException;

/**
 * Class representing a list of reviews.
 */
public class ReviewList {
    private List<Review> reviews;

    /**
     * Constructor for ReviewList class.
     * @param reviews the list of reviews
     */
    public ReviewList(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Constructor for ReviewList class with an empty list.
     */
    public ReviewList() {
        this.reviews = new ArrayList<>();
    }

    /**
     * Adds a review to the list.
     * @param review the review to add
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Removes a review from the list at the specified index.
     * @param index the index of the review to remove
     * @throws InvalidArgumentException if the index is invalid
     */
    public Review removeReview(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid review index!");
        }
        return reviews.remove(index - 1);
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @return  true if the index is valid, false otherwise
     */
    private boolean isInvalidIndex(int index) {
        return index < 1 || index > reviews.size();
    }

    /**
     * Returns a string representation of the list of reviews.
     * @return a string representation of the list of reviews
     */
    @Override
    public String toString() {
        if (reviews.isEmpty()) {
            return "No reviews yet!";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Review review : reviews) {
            sb.append(i).append(".\n").append(review).append("\n");
        }

        return sb.toString().stripTrailing();
    }
}
