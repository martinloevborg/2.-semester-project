package domain;

import domain.model.Account;
import domain.model.Privilege;
import interfaces.IAuthenticator;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extends the AbstractController to provide functionally to work with accounts. Used to authenticate users.
 */
public class AccountController extends AbstractController<Integer, Account> implements IAuthenticator {
    /**
     * The account which is currently signed in.
     */
   Account currentUser = null;
    /**
     * The privilege of the currently signed in account.
     */
   Privilege userPrivilege = null;

    /**
     * Reloads the data and updates the map to display accounts.
     */
    @Override
    protected void reloadData() {
        super.reloadData();

        super.map = super.getDataContext().getAccounts();
    }

    /**
     * Gets the entries based on upon the specified
     * @param key the id to to filter the data.
     * @return the filtered map.
     */
    @Override
    public Map<Integer, Account> getEntries(Object key) {
        Map<Integer, Account> result = new TreeMap<>();
        if (key instanceof Integer) {
            int id = (int) key;
            for (Map.Entry<Integer, Account> e : super.map.entrySet()) {
                if (e.getValue().getId() == id) {
                    result.put(e.getKey(), e.getValue());
                }
            }
        }
        return result;

    }

    /**
     * Deletes the specified account. Issues a soft delete.
     * @param account the account to delete.
     * @return a boolean with the status of the procedure. Returns true if successful.
     */
    @Override
    public boolean delete(Account account) {
        Account a = new Account(account.getId(), account.getUsername(), account.getPassword(), account.getPrivilegeId(), true);
        if (super.getDataConnection().update(a)) {
            reloadData();
            return true;
        }
        return false;
    }

    /**
     * Validates the provided credentials.
     * @param username the username to validate.
     * @param password the password to validate
     * @return a boolean with the status. Returns true if successfully logged in.
     */
    @Override
    public boolean login(String username, String password) {
        for (Account a : map.values()) {
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
                currentUser = a;
                userPrivilege = super.getDataContext().getPrivileges().get(a.getPrivilegeId());
                return true;
            }
        }
        return false;
    }

    /**
     * Signs the current account out.
     */
    @Override
    public void logout() {
        currentUser = null;
    }

    /**
     * Gets the currently signed in account.
     * @return the currently signed in account.
     */
    @Override
    public Account getLoggedInAccount() {
        return currentUser;
    }

    /**
     * Gets the privilege of the currently signed in account.
     * @return the privilege of the currently signed in account.
     */
    @Override
    public Privilege getLoggedInAccountPrivilege(){
        return userPrivilege;
    }
}
