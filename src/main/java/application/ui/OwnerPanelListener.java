package application.ui;

/**
 * Listener interface for owner panel events.
 */
public interface OwnerPanelListener {
    /**
     * Called when filter is applied.
     *
     * @param includeTags the tags to include in the filter
     * @param excludeTags the tags to exclude from the filter
     * @param status the status of the reviews to include in the filter
     * @param conditions the conditions to filter by, e.g. "food > 3.5"
     */
    void onFilterApplied(String includeTags,
                         String excludeTags,
                         String status,
                         String conditions
    );

    /**
     * Called when sort is applied.
     *
     * @param sortBy the criterion to sort by
     * @param sortOrder the order to sort in, either "Ascending" or "Descending"
     */
    void onSortApplied(String sortBy, String sortOrder);

    /**
     * Called when resolve action is requested.
     *
     * @param rowIndex the row index of the review to resolve
     */
    void onResolveReview(int rowIndex);

    /**
     * Called when unresolve action is requested.
     *
     * @param rowIndex the row index of the review to unresolve
     */
    void onUnresolveReview(int rowIndex);

    /**
     * Called when add tag action is requested.
     *
     * @param rowIndex the row index of the review to add tags to
     */
    void onAddTagReview(int rowIndex);

    /**
     * Called when delete tag action is requested.
     *
     * @param rowIndex the row index of the review to delete tags from
     */
    void onDeleteTagReview(int rowIndex);

    /**
     * Called when delete action is requested.
     *
     * @param rowIndex the row index of the review to delete
     */
    void onDeleteReview(int rowIndex);

    /**
     * Called when refresh is requested.
     */
    void onRefresh();

    /**
     * Called when logout is requested.
     */
    void onLogout();
}
