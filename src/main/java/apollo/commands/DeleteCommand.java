package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Deadline;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.tasks.ToDo;
import apollo.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private static final String PATTERN = "^delete\\s+(\\d+)$";
    private Matcher matcher;

    public DeleteCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("delete", "delete <task number>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        int id = Integer.parseInt(matcher.group(1)) - 1;
        Task task = taskList.getTask(id);

        if (task == null) {
            throw new ApolloException.TaskNotFoundException(id);
        }

        taskList.removeTask(id);
        ui.delete(task, taskList.size());

        setUndoExecutable(() -> {
            taskList.addTask(task);
            ui.add(task, taskList.size());
        });
    }
}
