package apollo.commands;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Deadline;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to create a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String PATTERN = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
    private Matcher matcher;

    public DeadlineCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("deadline", "deadline <DESCRIPTION> /by <DATE>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        try {
            Task task = new Deadline(matcher.group(1), matcher.group(2));
            taskList.addTask(task);
            int size = taskList.size();
            ui.add(task, size);

            DeleteCommand cmd = new DeleteCommand("delete " + size);
            setUndoExecutable(() -> cmd.run(ui, taskList));
        } catch (DateTimeParseException e) {
            throw new ApolloException.InvalidDateFormatException();
        }
    }
}
