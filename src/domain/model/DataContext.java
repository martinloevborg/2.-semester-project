package domain.model;

import interfaces.IDataContext;

import java.util.Map;
import java.util.TreeMap;

/**
 * Stores all records of the database.
 */
public class DataContext implements IDataContext {
    /**
     * Stores the credits.
     */
    private Map<Credit.Key, Credit> credits;
    /**
     * Stores the persons.
     */
    private Map<Integer, Person> persons;
    /**
     * Stores the privileges.
     */
    private Map<Integer, Privilege> privileges;
    /**
     * Stores the productions
     */
    private Map<Integer, Production> productions;
    /**
     * Stores the roles.
     */
    private Map<Integer, Role> roles;
    /**
     * Stores the accounts.
     */
    private Map<Integer, Account> accounts;

    /**
     * Sole constructor.
     */
    public DataContext() {
        credits = new TreeMap<>();
        persons = new TreeMap<>();
        privileges = new TreeMap<>();
        productions = new TreeMap<>();
        roles = new TreeMap<>();
        accounts = new TreeMap<>();
    }

    /**
     * Gets the credits.
     * @return map with the key and data object of the credits.
     */
    @Override
    public Map<Credit.Key, Credit> getCredits() {
        return credits;
    }

    /**
     * Gets the persons.
     * @return map with the key and the data object of the persons.
     */
    @Override
    public Map<Integer, Person> getPersons() {
        return persons;
    }

    /**
     * Gets the privileges.
     * @return map with the key and the data object of the privileges.
     */
    @Override
    public Map<Integer, Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * Gets the productions.
     * @return map with the key and the data object of the productions.
     */
    @Override
    public Map<Integer, Production> getProductions() {
        return productions;
    }

    /**
     * Gets the roles.
     * @return map with the key and the data object of the roles.
     */
    @Override
    public Map<Integer, Role> getRoles() {
        return roles;
    }

    /**
     * Gets the accounts.
     * @return map with the key and the data object of the accounts.
     */
    @Override
    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
}
