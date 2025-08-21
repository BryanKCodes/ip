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
        String command = input.toLowerCase();
        if (command.equals("bye")) {
            Ui.exit();
            return false;
        } else if (command.equals("list")) {
            Ui.list(taskList);
        } else if (command.startsWith("mark ")) {
            int id = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = taskList.getTask(id);
            if (task == null) {
                Ui.showMessage("Unable to find task " + (id + 1));
            } else {
                task.markAsDone();
                Ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            }
        } else if (command.startsWith("unmark ")) {
            int id = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = taskList.getTask(id);
            if (task == null) {
                Ui.showMessage("Unable to find task " + (id + 1));
            } else {
                task.markAsUndone();
                Ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
        } else {
            Ui.add(taskList, input);
        }

        return true;
    }
}
