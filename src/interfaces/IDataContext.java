package interfaces;

import domain.model.*;

import java.util.Map;

/**
 * Interface used by the domain to reflect all records in the database as objects in maps.
 */
public interface IDataContext {
    /**
     * Gets the credits.
     * @return map with the key and data object of the credits.
     */

    Map<Credit.Key, Credit> getCredits();
    /**
     * Gets the persons.
     * @return map with the key and the data object of the persons.
     */
    Map<Integer, Person> getPersons();

    /**
     * Gets the privileges.
     * @return map with the key and the data object of the privileges.
     */
    Map<Integer, Privilege> getPrivileges();

    /**
     * Gets the productions.
     * @return map with the key and the data object of the productions.
     */
    Map<Integer, Production> getProductions();

    /**
     * Gets the roles.
     * @return map with the key and the data object of the roles.
     */
    Map<Integer, Role> getRoles();

    /**
     * Gets the accounts.
     * @return map with the key and the data object of the accounts.
     */
    Map<Integer, Account> getAccounts();
}