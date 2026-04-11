package application.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AuthManager class.
 */
public class AuthManagerTest {

    /**
     * Tests the constructor of AuthManager with a valid password.
     */
    @Test
    public void constructor_validPassword_success() {
        // Partition: Valid password string
        AuthManager manager = new AuthManager("password");
        assertNotNull(manager);
        assertFalse(manager.isOwnerAuthenticated());
    }

    /**
     * Tests the constructor of AuthManager with an invalid password.
     */
    @Test
    public void constructor_nullPassword_throwsException() {
        // Partition: Invalid input (null password)
        assertThrows(IllegalArgumentException.class, () -> new AuthManager(null));
    }

    /**
     * Tests the constructor of AuthManager with an invalid password.
     */
    @Test
    public void constructor_blankPassword_throwsException() {
        // Partition: Invalid input (blank password)
        assertThrows(IllegalArgumentException.class, () -> new AuthManager("   "));
    }

    /**
     * Tests the authenticateOwner method of AuthManager.
     */
    @Test
    public void authenticateOwner_correctPassword_returnsTrue() {
        // Partition: Correct password
        AuthManager manager = new AuthManager("secret");
        assertTrue(manager.authenticateOwner("secret"));
        assertTrue(manager.isOwnerAuthenticated());
    }

    /**
     * Tests the authenticateOwner method of AuthManager.
     */
    @Test
    public void authenticateOwner_incorrectPassword_returnsFalse() {
        // Partition: Incorrect password
        AuthManager manager = new AuthManager("secret");
        assertFalse(manager.authenticateOwner("wrong"));
        assertFalse(manager.isOwnerAuthenticated());
    }

    /**
     * Tests the authenticateOwner method of AuthManager.
     */
    @Test
    public void authenticateOwner_nullPassword_returnsFalse() {
        // Partition: Null password input
        AuthManager manager = new AuthManager("secret");
        assertFalse(manager.authenticateOwner(null));
        assertFalse(manager.isOwnerAuthenticated());
    }

    /**
     * Tests the logout method of AuthManager.
     */
    @Test
    public void logout_authenticatedSession_setsAuthenticatedToFalse() {
        // Partition: Logged in session
        AuthManager manager = new AuthManager("secret");
        manager.authenticateOwner("secret");
        assertTrue(manager.isOwnerAuthenticated());
        manager.logout();
        assertFalse(manager.isOwnerAuthenticated());
    }
}
