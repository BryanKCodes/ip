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
public class EventCommand extends TaskCommand {
    private static final String PATTERN = "^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$";
    private Matcher matcher;

    public EventCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("event",
                    "event <description> /from <START> /to <END>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        try {
            Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
            taskList.addTask(task);
            int size = taskList.size();;
            ui.add(task, size);

            DeleteCommand cmd = new DeleteCommand("delete " + size);
            setUndoExecutable(() -> cmd.run(ui, taskList));
        } catch (DateTimeParseException e) {
            throw new ApolloException.InvalidDateFormatException();
        }
    }
}
