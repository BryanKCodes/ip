package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to close the app.
 */
public class ByeCommand extends Command {
    private static final String PATTERN = "^bye$";
    private Matcher matcher;

    public ByeCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("leave", "bye");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        ui.exit();
    }
}
