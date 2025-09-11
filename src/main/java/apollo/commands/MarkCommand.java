package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String PATTERN = "^mark\\s+(\\d+)$";
    private Matcher matcher;

    public MarkCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("mark", "mark <task number>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        int id = Integer.parseInt(matcher.group(1)) - 1;
        Task task = taskList.getTask(id);

        if (task == null) {
            throw new ApolloException.TaskNotFoundException(id);
        }

        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + task);

        UnmarkCommand cmd = new UnmarkCommand("unmark " + (id + 1));
        setUndoExecutable(() -> cmd.run(ui, taskList));
    }
}
