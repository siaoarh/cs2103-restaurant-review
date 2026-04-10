package application.command;


import application.auth.AuthManager;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Represents a login command.
 */
public class LoginCommand extends Command {
    private final String password;

    /**
     * Constructor for LoginCommand class.
     *
     * @param password the password to authenticate with
     */
    public LoginCommand(String password) {
        this.password = password;
    }

    @Override
    public boolean requiresOwnerAuthentication() {
        return false;
    }

    /**
     * Executes the login command.
     *
     * @param reviews the list of reviews
     * @param storage the storage object
     * @param manager the authentication manager
     * @return a string indicating the result of the login command
     */
    @Override
    public String execute(
            ReviewList reviews,
            Storage storage,
            AuthManager manager
    ) {
        if (manager.isOwnerAuthenticated()) {
            return "You are already logged in!";
        }

        boolean isLoggedIn = manager.authenticateOwner(password);
        return isLoggedIn ? "Successfully logged in!" : "Incorrect password!";
    }
}
