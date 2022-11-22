package domain.model;

/**
 * Holds information about a single person record in the database.
 */
public class Person implements Comparable<Person> {
    /**
     * The ID of the person.
     */
    private final int ID;
    /**
     * The name of the person.
     */
    private String name;

    /**
     * Sole constructor. Maps all required fields.
     * @param id the id of the person.
     * @param name the name of the person.
     */
    public Person(final int id, String name) {
        ID = id;
        this.name = name;
    }

    /**
     * Gets the id of the person.
     * @return an integer containing the id.
     */
    public int getId() {
        return ID;
    }

    /**
     * Gets the name of the person.
     * @return a string containing the name of the person.
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
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return ID == p.ID;
        }
        return false;
    }

    /**
     * Compares another person to this instance to determine if they are identical. Used by the Comparable interface.
     * @param p the other person to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Person p) {
        return ID - p.ID;
    }

    /**
     * Displays the name of the person.
     * @return a string with the name.
     */
    @Override
    public String toString() {
        return name;
    }
}
