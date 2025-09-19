package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to mark a task as undone.
 */
public class UnmarkCommand extends TaskCommand {
    private static final String PATTERN = "^unmark\\s+(\\d+)$";
    private Matcher matcher;

    public UnmarkCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("unmark", "unmark <TASK_NUMBER>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        int id = Integer.parseInt(matcher.group(1)) - 1;
        Task task = taskList.getTask(id);

        if (task == null) {
            throw new ApolloException.TaskNotFoundException(id);
        }

        task.markAsUndone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);

        MarkCommand cmd = new MarkCommand("mark " + (id + 1));
        setUndoExecutable(() -> cmd.run(ui, taskList));
    }
}
