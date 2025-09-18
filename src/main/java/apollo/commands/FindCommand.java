package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private static final String PATTERN = "^find\\s+(.+)$";
    private Matcher matcher;

    public FindCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("find", "find <KEYWORD>");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        String keyword = matcher.group(1);
        TaskList filteredTaskList = new TaskList(taskList.findTasks(keyword));
        ui.showMessage("Here are the matching tasks in your list:\n" + filteredTaskList);
    }
}
