package application.command;

import application.auth.AuthManager;
import application.review.ReviewList;
import application.storage.Storage;

/**
 * Class representing a command to log out.
 */
public class LogoutCommand extends Command {
    @Override
    public boolean requiresOwnerAuthentication() {
        return false;
    }

    /**
     * Executes the logout command.
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
            manager.logout();
            return new CommandResult(
                    "Successfully logged out!",
                    isTerminatingCommand(),
                    reviews
            );
        }

        return new CommandResult(
                "You are not logged in!",
                isTerminatingCommand(),
                reviews
        );
    }
}
