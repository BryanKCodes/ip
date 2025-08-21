import java.util.Scanner;

public class Apollo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        Ui.greet();

        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            running = parser.handle(input);
        }

        scanner.close();
    }
}
