package apollo;

import apollo.exception.ApolloException;
import apollo.parser.Parser;
import apollo.ui.Ui;

import java.util.Scanner;

public class Apollo {
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
