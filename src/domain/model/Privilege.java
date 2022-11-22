package domain.model;

/**
 * Holds information about a single privilege in the database.
 */
public class Privilege implements Comparable<Privilege> {
    /**
     * The ID of the privilege.
     */
    private final int ID;
    /**
     * The name of the privilege.
     */
    private String name;

    /**
     * Sole constructor. Maps all required fields.
     * @param id the id of the privilege.
     * @param name the name of the privilege.
     */
    public Privilege(final int id, String name) {
        ID = id;
        this.name = name;
    }

    /**
     * Gets the id of the privilege.
     * @return an integer containing the id.
     */
    public int getId() {
        return ID;
    }

    /**
     * Gets the name of the privilege.
     * @return a string containing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares another object to this instance to determine if they are identical.
     * @param obj the other object to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Privilege) {
            Privilege p = (Privilege) obj;
            return ID == p.ID;
        }
        return false;
    }

    /**
     * Compares another privilege to this instance to determine if they are identical. Used by the Comparable interface.
     * @param o the other privilege to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Privilege o) {
        return ID - o.ID;
    }
}
