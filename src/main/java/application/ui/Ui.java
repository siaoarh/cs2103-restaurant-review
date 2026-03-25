package application.ui;

import java.util.Scanner;

/**
 * Ui class containing methods for interacting with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the command prompt.
     */
    public void showPrompt() {
        System.out.print("> ");
    }

    /**
     * Reads the next command from the user.
     *
     * @return the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
