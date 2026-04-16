package application.ui;

/**
 * Listener interface for patron panel events.
 */
public interface PatronPanelListener {
    /**
     * Called when a review is submitted. Returns output message to display.
     *
     * @param body the review body text
     * @param food the food score
     * @param clean the cleanliness score
     * @param service the service score
     * @param tagsAsString the tags to add to the review, as a string
     * @return the output message from the backend
     */
    String onReviewSubmitted(String body, double food, double clean,
                             double service, String tagsAsString);
}
