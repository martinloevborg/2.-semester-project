package domain.model;

/**
 * Holds information about a single production record in the database.
 */
public class Production implements Comparable<Production> {
    /**
     * The ID of the production.
     */
    private final int ID;
    /**
     * The production ID of the production. This field is not a key, but is possible to edit.
     */
    private int productionId;
    /**
     * The name of the production-
     */
    private String name;
    /**
     * The ID of the account which created the production.
     */
    private final int USER_ID;
    /**
     * A boolean to determine if the administrators has approved the credits list.
     */
    private boolean approvedCredits;
    /**
     * A boolean to determine whether the production has been deleted.
     */
    private boolean disabled;

    /**
     * Sole constructor. Maps all required fields.
     * @param id the id of the production.
     * @param productionId the editable production id of the production.
     * @param name the name of the production.
     * @param userId the id of the account which created the production.
     * @param approvedCredits a status to determine whether the credits have been approved.
     * @param disabled a status to determine if the production has been deleted.
     */
    public Production(final int id, int productionId, String name, final int userId, boolean approvedCredits, boolean disabled) {
        ID = id;
        this.productionId = productionId;
        this.name = name;
        this.USER_ID = userId;
        this.approvedCredits = approvedCredits;
        this.disabled = disabled;
    }

    /**
     * Gets the id of the production.
     * @return an integer containing the id.
     */
    public int getId() {
        return ID;
    }

    /**
     * Gets the production id of the production
     * @return an integer containing the production id.
     */
    public int getProductionId() {
        return productionId;
    }

    /**
     * Gets the name of the production.
     * @return a string containing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the id of the account which created the production.
     * @return an integer containing the id of the creator's account.
     */
    public int getUserId() {
        return USER_ID;
    }

    /**
     * Gets a status to determine if the credits have been approved.
     * @return an integer containing the status about whether the credits have been approved.
     */
    public boolean isApprovedCredits() {
        return approvedCredits;
    }

    /**
     * Gets a status to determine if the production has been soft deleted.
     * @return an integer containing the status of the soft deletion.
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Compares another object to this instance to determine if they are identical.
     * @param obj the other object to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Production) {
            Production p = (Production) obj;
            return ID == p.ID;
        }
        return false;
    }

    /**
     * Compares another production to this instance to determine if they are identical. Used by the Comparable interface.
     * @param p the other production to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Production p) {
        return ID - p.ID;
    }

    /**
     * Displays essential information about the production.
     * @return a string displaying the name and production id of the production.
     */
    @Override
    public String toString() {
        return name + ", produktionsid " + productionId;
    }
}
