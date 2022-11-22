package domain.model;

/**
 * Holds information about a single account record in the database.
 */
public class Account implements Comparable<Account> {
    /**
     * The ID of the account.
     */
    private final int ID;
    /**
     * The username of the account.
     */
    private final String USERNAME;
    /**
     * The password of the account.
     */
    private String password;
    /**
     * The privilege of the account. Should compare with Privilege.
     */
    private int privilegeId;
    /**
     * Determines whether the account has been deleted.
     */
    private boolean deleted;

    /**
     * Sole constructor. Maps all required fields.
     * @param id the ID of the account.
     * @param username the username of the account
     * @param password the password of the account.
     * @param privilegeId the id of the privilege to associate with the account
     * @param deleted a flag to determine whether or not the account has been deleted.
     */
    public Account(final int id, String username, String password, int privilegeId, boolean deleted) {
        this.ID = id;
        this.USERNAME = username;
        this.password = password;
        this.privilegeId = privilegeId;
        this.deleted = deleted;
    }

    /**
     * Gets the ID of the account.
     * @return an integer containing the ID.
     */
    public int getId() {
        return ID;
    }

    /**
     * Gets the username of the account.
     * @return a string containing the username.
     */
    public String getUsername() {
        return USERNAME;
    }

    /**
     * Gets the password of the account.
     * @return a string containing the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the ID of the privilege of the account
     * @return an integer containing the ID of the privilege.
     */
    public int getPrivilegeId() {
        return privilegeId;
    }

    /**
     * Gets the boolean which reflects whether the account has been deleted.
     * @return a boolean containing the status of the account.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Compares another object to the this instance to determine if they are identical.
     * @param obj the object to compare against.
     * @return a boolean with the result of the comparison. Returns true if they are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account a = (Account) obj;
            return ID == a.ID;
        }
        return false;
    }

    /**
     * Compares another account to the this instance to determine if they are identical. Used by the Comparable interface.
     * @param a the other account to compare against.
     * @return a boolean with the result of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Account a) {
        return ID - a.ID;
    }
}
