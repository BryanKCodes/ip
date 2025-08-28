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

        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            try {
                running = parser.handle(input);
            } catch (ApolloException e) {
                Ui.showMessage(e.getMessage());
            }
        }

        scanner.close();
    }
}
