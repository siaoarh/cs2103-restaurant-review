package application.review;

/**
 * Class representing a rating.
 */
public class Rating {
    private float rating;

    /**
     * Constructor for Rating class.
     * @param rating the rating of the review
     */
    public Rating(float rating) {
        this.rating = rating;
    }

    /**
     * Checks if the rating is valid.
     * @param rating the rating to check
     * @return true if the rating is valid, false otherwise
     */
    public static boolean isValidRating(float rating) {
        return rating >= 0 && rating <= 5;
    }

    /**
     * Getter for rating value.
     * @return the float value of the rating
     */
    public float getRatingValue() {
        return rating;
    }

    /**
     * Returns a string representation of the rating.
     * @return a string representation of the rating
     */
    @Override
    public String toString() {
        return String.format("Rating: %.1f", rating);
    }
}
