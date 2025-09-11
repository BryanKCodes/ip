package apollo.commands;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to create a new Event task.
 */
public class EventCommand extends Command {
    private static final String PATTERN = "^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$";
    private Matcher matcher;

    public EventCommand() {
        super(PATTERN);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("event",
                    "event <description> /from <start> /to <end>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        try {
            Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
            taskList.addTask(task);
            ui.add(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new ApolloException.InvalidDateFormatException();
        }
    }
}
