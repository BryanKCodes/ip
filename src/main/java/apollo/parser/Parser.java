package apollo.parser;

import apollo.storage.Storage;
import apollo.ui.Ui;
import apollo.exception.ApolloException;
import apollo.tasks.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing and processing of user input commands.
 * Converts textual commands into actions on the TaskList and interacts with Ui and Storage.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Constructs a Parser and loads the TaskList from storage.
     * If loading fails, initializes an empty TaskList.
     */
    public Parser() {
        try {
            taskList = Storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    /**
     * Processes the given user input and executes the corresponding action.
     *
     * @param input User input string.
     * @return true if the program should continue, false if it should exit.
     * @throws ApolloException If the input format is invalid or refers to a non-existent task.
     */
    public boolean handle(String input) throws ApolloException {
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

    /**
     * Processes user input using regular expressions for commands such as mark, unmark, todo, deadline, event, and delete.
     *
     * @param input User input string.
     * @throws ApolloException If the input format is invalid, refers to a non-existent task, or uses invalid date formats.
     */
    private void handleRegex(String input) throws ApolloException {
        Matcher matcher;
        String command = input.toLowerCase();

        // MARK
        if (command.startsWith("mark")) {
            matcher = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1)) - 1;
                Task task = taskList.getTask(id);
                if (task == null) {
                    throw new ApolloException.TaskNotFoundException(id);
                } else {
                    task.markAsDone();
                    Ui.showMessage("Nice! I've marked this task as done:\n  " + task);
                    safeSave();
                }
            } else {
                throw new ApolloException.InvalidFormatException("mark", "mark <task number>");
            }
            return;
        }

        // UNMARK
        if (command.startsWith("unmark")) {
            matcher = Pattern.compile("^unmark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1)) - 1;
                Task task = taskList.getTask(id);
                if (task == null) {
                    throw new ApolloException.TaskNotFoundException(id);
                } else {
                    task.markAsUndone();
                    Ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
                    safeSave();
                }
            } else {
                throw new ApolloException.InvalidFormatException("unmark", "unmark <task number>");
            }
            return;
        }

        // TODO
        if (command.startsWith("todo")) {
            matcher = Pattern.compile("^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                Task task = new ToDo(matcher.group(1));
                taskList.addTask(task);
                Ui.add(task, taskList.size());
                safeSave();
            } else {
                throw new ApolloException.EmptyDescriptionException("todo", "todo <description>");
            }
            return;
        }

        // DEADLINE
        if (command.startsWith("deadline")) {
            matcher = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                try {
                    Task task = new Deadline(matcher.group(1), matcher.group(2));
                    taskList.addTask(task);
                    Ui.add(task, taskList.size());
                    safeSave();
                } catch (DateTimeParseException e) {
                    throw new ApolloException.InvalidDateFormatException();
                }
            } else {
                throw new ApolloException.InvalidFormatException("deadline", "deadline <description> /by <time>");
            }
            return;
        }

        // EVENT
        if (command.startsWith("event")) {
            matcher = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                try {
                    Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                    taskList.addTask(task);
                    Ui.add(task, taskList.size());
                    safeSave();
                } catch (DateTimeParseException e) {
                    throw new ApolloException.InvalidDateFormatException();
                }
            } else {
                throw new ApolloException.InvalidFormatException("event", "event <description> /from <start> /to <end>");
            }
            return;
        }

        // DELETE
        if (command.startsWith("delete")) {
            matcher = Pattern.compile("^delete\\s+(\\d+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1)) - 1;
                Task task = taskList.getTask(id);
                if (task == null) {
                    Ui.showMessage("Unable to find task " + (id + 1));
                } else {
                    taskList.removeTask(id);
                    Ui.delete(task, taskList.size());
                    safeSave();
                }
            } else {
                throw new ApolloException.InvalidFormatException("delete", "delete <task number>");
            }
            return;
        }

        // FIND
        if (input.toLowerCase().startsWith("find")) {
            matcher = Pattern.compile("^find\\s+(.+)$", Pattern.CASE_INSENSITIVE).matcher(input);
            if (matcher.matches()) {
                String keyword = matcher.group(1);
                TaskList filteredTaskList = new TaskList(taskList.findTasks(keyword));
                Ui.showMessage("Here are the matching tasks in your list:\n" + filteredTaskList.toString());
            } else {
                throw new ApolloException.InvalidFormatException("find", "find <keyword>");
            }
            return;
        }

        // fallback
        throw new ApolloException.UnknownCommandException();
    }

    /**
     * Saves the current TaskList to storage, displaying an error message if saving fails.
     */
    private void safeSave() {
        try {
            Storage.save(taskList);
        } catch (IOException e) {
            Ui.showMessage("Unable to save your tasks. Changes may be lost.");
        }
    }
}
