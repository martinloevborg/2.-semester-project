package domain.model;

/**
 * Holds information about a single role record in the database.
 */
public class Role implements Comparable<Role> {
    /**
     * The ID of the role
     */
    private final int ID;
    /**
     * The name of the role.
     */
    private String name;

    /**
     * Sole constructor. Maps all required fields.
     * @param id the id of the role.
     * @param name the name of the role.
     */
    public Role(final int id, String name) {
        ID = id;
        this.name = name;
    }

    /**
     * Gets the id of the role
     * @return an integer containing the id.
     */
    public int getId() {
        return ID;
    }

    /**
     * Gets the name of the role.
     * @return a string containing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets teh name of the role
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares another object to this instance to determine if they are identical.
     * @param obj the other object to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role r = (Role) obj;
            return ID == r.ID;
        }
        return false;
    }

    /**
     * Compares another role to this instance to determine if they are identical. Used by the Comparable interface.
     * @param r the other role to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Role r) {
        // Sort in alphabetical order.
        return ID - r.ID;
    }

    /**
     * Displays the name of the role.
     * @return a string containing the name of the role.
     */
    @Override
    public String toString() {
        return name;
    }
}
