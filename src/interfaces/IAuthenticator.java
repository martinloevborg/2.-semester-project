package interfaces;

import domain.model.Account;
import domain.model.Privilege;

/**
 * Interface used between presentation and domain to sign in users.
 */
public interface IAuthenticator {
    /**
     * Validates the provided credentials.
     * @param username the username to validate.
     * @param password the password to validate
     * @return a boolean with the status. Returns true if successfully logged in.
     */
    boolean login(String username, String password);
    /**
     * Signs the current account out.
     */
    void logout();
    /**
     * Gets the currently signed in account.
     * @return the currently signed in account.
     */
    Account getLoggedInAccount();
    /**
     * Gets the privilege of the currently signed in account.
     * @return the privilege of the currently signed in account.
     */
    Privilege getLoggedInAccountPrivilege();
}