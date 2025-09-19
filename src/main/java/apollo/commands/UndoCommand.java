package apollo.commands;

import java.util.regex.Matcher;

import apollo.exception.ApolloException;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command to undo the previous command.
 */
public class UndoCommand extends TaskCommand {
    private static final String PATTERN = "^undo$";
    private Matcher matcher;

    public UndoCommand(String input) {
        super(PATTERN, input);
    }

    @Override
    public void match(String input) throws ApolloException {
        matcher = super.matcher(input);
        if (!matcher.matches()) {
            throw new ApolloException.InvalidFormatException("undo", "undo");
        }
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ApolloException {
        CommandStack.undo();
    }
}
