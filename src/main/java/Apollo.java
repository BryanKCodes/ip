import java.util.Scanner;

public class Apollo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui.greet();

        boolean running = true;
        while (running) {
            String input = scanner.nextLine().trim();
            running = Parser.handle(input);
        }

        scanner.close();
    }
}
