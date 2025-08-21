public class Parser {
    /**
     * Handles user input.
     * @param input The user input string.
     * @return true if program should continue, false if it should exit.
     */
    public static boolean handle(String input) {
        switch(input.toLowerCase()) {
            case "bye":
                Ui.exit();
                return false;
            default:
                Ui.showMessage(input);
                return true;
        }
    }
}
