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
     * @return a {@code CommandResult} object containing the result of the command execution
     */
    @Override
    public CommandResult execute(
            ReviewList reviews,
            Storage storage,
            AuthManager manager
    ) {
        if (manager.isOwnerAuthenticated()) {
            return new CommandResult(
                    "You are already logged in!",
                    isTerminatingCommand(),
                    reviews
            );
        }

        boolean isLoggedIn = manager.authenticateOwner(password);
        return new CommandResult(
                isLoggedIn ? "Successfully logged in!" : "Incorrect password!",
                isTerminatingCommand(),
                reviews
        );
    }
}
