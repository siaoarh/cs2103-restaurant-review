package application.review;

/**
 * Class representing a review.
 */
public class Review {
    private String user;
    private String review;
    private Rating rating;

    /**
     * Constructor for Review class.
     * @param user the author of the review
     * @param review the content of the review
     * @param rating the rating of the review
     */
    public Review(String user, String review, Rating rating) {
        this.user = user;
        this.review = review;
        this.rating = rating;
    }

    /**
     * Getter for user.
     * @return the user of the review
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter for review.
     * @return the content of the review
     */
    public String getReview() {
        return review;
    }

    /**
     * Getter for rating.
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Returns a string representation of the review.
     * @return a string representation of the review
     */
    @Override
    public String toString() {
        return String.format(
                "Review by: %s\n%s\nReview: %s",
                getUser(),
                getRating(),
                getReview()
        );
    }
}
