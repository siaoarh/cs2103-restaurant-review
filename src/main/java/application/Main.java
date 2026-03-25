package application;

import application.ui.Ui;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui();
        MealMeter mealMeter = new MealMeter();

        ui.showMessage(mealMeter.getWelcomeMessage());

        boolean shouldContinue = true;
        while (shouldContinue) {
            ui.showPrompt();
            String userInput = ui.readCommand();

            String response = mealMeter.getResponse(userInput);
            ui.showMessage(response);

            shouldContinue = !mealMeter.isExit(userInput);
        }
    }
}
