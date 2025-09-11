package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

public class DeleteCommand extends Command {
    private static final String PATTERN = "^delete\\s+(\\d+)$";
    private Matcher matcher;

    public DeleteCommand() {
        super(PATTERN);
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
    }
}
