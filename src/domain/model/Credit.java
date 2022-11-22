package domain.model;

/**
 * Holds information about a single credit record in the database.
 */
public class Credit  implements Comparable<Credit> {
    /**
     * The key of the account.
     */
    private final Key ID;
    /**
     * The name of the person associated with the credit.
     */
    private String personName;
    /**
     * The role of the person.
     */
    private String roleName;

    /**
     * Sole constructor. Maps all required fields.
     * @param productionId the id of the production.
     * @param personId the id of the person.
     * @param roleId the id of the role.
     * @param personName the name of the person.
     * @param roleName the name of the role.
     */
    public Credit(int productionId, int personId, int roleId, String personName, String roleName) {
        ID = new Key(productionId, personId, roleId);
        this.personName = personName;

        this.roleName = roleName;
    }

    /**
     * Gets the key of the credit.
     * @return the key of the record.
     */
    public Key getId() {
        return ID;
    }

    /**
     * Gets the name of the person of the credit.
     * @return a string containing the name of the person.
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Gets the name of the role of the credit.
     * @return a string containing the name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Compares another object to this instance to determine if they are identical.
     * @param obj the other object to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Credit) {
            Credit c = (Credit) obj;
            return ID.equals(c.ID);
        }
        return false;
    }

    /**
     * Compares another credit to this instance to determine if they are identical. Used by the Comparable interface.
     * @param o the other instance to compare against.
     * @return a boolean with the status of the comparison. Returns true if they are identical.
     */
    @Override
    public int compareTo(Credit o) {
        return ID.personId - o.ID.personId;
    }


    /**
     * Holds information about the key fields of the record.
     */
    public static class Key implements Comparable<Key> {
        /**
         * The production id of the key.
         */
        int productionId;
        /**
         * The person id of the key.
         */
        int personId;
        /**
         * The role id of the key.
         */
        int roleId;

        /**
         * Sole constructor. Maps all required fields.
         * @param productionId the production id of the key.
         * @param personId the person id of the key.
         * @param roleId the role id of the key.
         */
        public Key(int productionId, int personId, int roleId) {
            this.productionId = productionId;
            this.personId = personId;
            this.roleId = roleId;
        }

        /**
         * Gets the production id of the key.
         * @return an integer containing the production id.
         */
        public int getProductionId() {
            return productionId;
        }

        /**
         * Gets the person id of the key.
         * @return an integer containing the person id.
         */
        public int getPersonId() {
            return personId;
        }

        /**
         * Gets the role id of the key.
         * @return an integer containing the role id.
         */
        public int getRoleId() {
            return roleId;
        }

        /**
         * Compares another object to this instance to determine if they are identical.
         * @param obj the other object to compare against.
         * @return a boolean with the status of the comparison. Returns true if they are identical.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                return productionId == key.productionId && personId == key.personId && roleId == key.roleId;
            }

            return false;
        }

        /**
         * Compares another key to this instance to determine if they are identical. Used by Comparable interface.
         * @param o the other instance to compare against.
         * @return a boolean with teh status of the comparison. Returns true if they are identical.
         */
        @Override
        public int compareTo(Key o) {
            int result = productionId - o.productionId;
            if (result == 0)
                result = personId - o.personId;
            if (result == 0)
                result = roleId - o.roleId;
            return result;
        }

    }

    /**
     * Displays essential information about the credit.
     * @return a string formatted to display the name and role of the credit.
     */
    @Override
    public String toString() {
        return personName + ", " + roleName;
    }
}

