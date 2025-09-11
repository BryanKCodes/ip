package apollo.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apollo.exception.ApolloException;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents an executable command.
 * This abstract class provides a base for all specific command implementations.
 */
public abstract class Command {
    private final Pattern pattern;
    private String input;
    private Executable undoExecutable;

    /**
     * Constructs a command with a specific regex pattern to match user input.
     *
     * @param pattern The regex pattern for this command.
     */
    protected Command(String pattern, String input) {
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        this.input = input;
    }

    /**
     * Creates a Regex Matcher that can be used to match the given input
     *
     * @param input The user input string.
     * @return A Matcher for this command’s pattern and the input.
     */
    public Matcher matcher(String input) {
        return pattern.matcher(input);
    };

    /**
     * Validates the given user input against this command's pattern.
     *
     * @param input The user input string.
     * @throws ApolloException If the input does not match the expected format.
     */
    public abstract void match(String input) throws ApolloException;

    /**
     * Executes the specific action of the command.
     *
     * @param ui The user interface to interact with.
     * @param taskList The list of tasks to operate on.
     * @throws ApolloException If an error occurs during execution.
     */
    public abstract void execute(Ui ui, TaskList taskList) throws ApolloException;

    /**
     * Runs the command by first validating the input and then
     * executing the command's behavior if validation succeeds.
     *
     * @param ui The user interface to interact with.
     * @param taskList The list of tasks to operate on.
     * @throws ApolloException If the input is invalid or execution fails.
     */
    public final void run(Ui ui, TaskList taskList) throws ApolloException {
        match(input);
        execute(ui, taskList);
    }

    public void setUndoExecutable(Executable executable) {
        undoExecutable = executable;
    }

    /**
     * Undoes the action done by this command.
     *
     * @throws UnsupportedOperationException If undo command not defined.
     */
    public void undo() throws ApolloException {
        if (undoExecutable == null) {
            throw new UnsupportedOperationException("Undo not supported for this command");
        } else {
            undoExecutable.execute();
        }
    }
}
