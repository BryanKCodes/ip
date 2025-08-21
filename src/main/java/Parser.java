public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handles user input.
     * @param input The user input string.
     * @return true if program should continue, false if it should exit.
     */
    public boolean handle(String input) {
        switch(input.toLowerCase()) {
            case "list":
                Ui.list(taskList);
                return true;
            case "bye":
                Ui.exit();
                return false;
            default:
                Ui.add(taskList, input);
                return true;
        }
    }
}
