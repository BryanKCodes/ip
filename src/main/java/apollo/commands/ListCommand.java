package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

public class ListCommand extends Command {
    private static final String PATTERN = "^list$";
    private Matcher matcher;

    public ListCommand() {
        super(PATTERN);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("list", "list");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        ui.list(taskList);
    }
}
