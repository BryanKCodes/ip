package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.tasks.ToDo;
import apollo.ui.Ui;

/**
 * Represents a command to create a new ToDo task.
 */
public class ToDoCommand extends Command {
    private static final String PATTERN = "^todo\\s+(.+)$";
    private Matcher matcher;

    public ToDoCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.EmptyDescriptionException("todo", "todo <description>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task task = new ToDo(matcher.group(1));
        taskList.addTask(task);
        int size = taskList.size();
        ui.add(task, size);

        DeleteCommand cmd = new DeleteCommand("delete " + size);
        setUndoExecutable(() -> cmd.run(ui, taskList));
    }
}
