package apollo.commands;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.Deadline;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

public class DeadlineCommand extends Command {
    private static final String PATTERN = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
    private Matcher matcher;

    public DeadlineCommand() {
        super(PATTERN);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("deadline", "deadline <description> /by <time>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        try {
            Task task = new Deadline(matcher.group(1), matcher.group(2));
            taskList.addTask(task);
            ui.add(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new ApolloException.InvalidDateFormatException();
        }
    }
}
