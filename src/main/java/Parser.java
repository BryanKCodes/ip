import java.util.regex.*;

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
        } else {
            handleRegex(input);
        }

        return true;
    }

    private void handleRegex(String input) {
        Matcher matcher;

        // MARK
        matcher = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(1)) - 1;
            Task task = taskList.getTask(id);
            if (task == null) {
                Ui.showMessage("Unable to find task " + (id + 1));
            } else {
                task.markAsDone();
                Ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            }
            return;
        }

        // UNMARK
        matcher = Pattern.compile("^unmark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(1)) - 1;
            Task task = taskList.getTask(id);
            if (task == null) {
                Ui.showMessage("Unable to find task " + (id + 1));
            } else {
                task.markAsUndone();
                Ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
            return;
        }

        // TODO
        matcher = Pattern.compile("^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            Task task = new ToDo(matcher.group(1));
            taskList.addTask(task);
            Ui.add(task, taskList.size());
            return;
        }

        // DEADLINE
        matcher = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            Task task = new Deadline(matcher.group(1), matcher.group(2));
            taskList.addTask(task);
            Ui.add(task, taskList.size());
            return;
        }

        // EVENT
        matcher = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
            taskList.addTask(task);
            Ui.add(task, taskList.size());
            return;
        }

        // fallback
        Ui.showMessage("Sorry, I can't process your message. Please try again.");
    }
}
