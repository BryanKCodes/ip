package apollo;

import apollo.exception.ApolloException;
import apollo.parser.Parser;
import apollo.ui.Ui;

import java.util.Scanner;

/**
 * Runs the Apollo chatbot application.
 * Initializes the parser and user interface, and manages the main input loop.
 */
public class Apollo {
    /**
     * Starts the Apollo chatbot program.
     * Initializes the parser and UI, and reads commands from the user until exit.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        Ui.greet();

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            try {
                isRunning = parser.handle(input);
            } catch (ApolloException e) {
                Ui.showMessage(e.getMessage());
            }
        }

        scanner.close();
    }
}
