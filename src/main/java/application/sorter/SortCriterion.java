package application.sorter;

import application.exception.InvalidArgumentException;
import application.parser.ArgumentParser;

/**
 * Enum representing the different criteria for sorting reviews.
 */
public enum SortCriterion {
    OVERALL_SCORE("overall"),
    FOOD_SCORE("food"),
    CLEANLINESS_SCORE("clean"),
    SERVICE_SCORE("service"),
    TAG_COUNT("tag");

    private final String sortCriterionString;

    /**
     * Constructor for SortCriterion enum.
     * @param sortCriterionString the string representation of the criterion
     */
    SortCriterion(String sortCriterionString) {
        this.sortCriterionString = sortCriterionString;
    }

    /**
     * Returns the sort criterion corresponding to the input string.
     * @param sortCriterionString the string representation of the criterion
     * @return the sort criterion corresponding to the input string
     */
    public static SortCriterion getSortCriterion(String sortCriterionString) {
        for (SortCriterion criterion : SortCriterion.values()) {
            if (criterion.sortCriterionString.startsWith(sortCriterionString)) {
                return criterion;
            }
        }
        return OVERALL_SCORE;
    }

    /**
     * Returns the string representation of the criterion.
     * @return the string representation of the criterion
     */
    @Override
    public String toString() {
        return sortCriterionString;
    }
}
