package apollo.commands;

import java.io.IOException;

import apollo.exception.ApolloException;
import apollo.storage.Storage;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Represents a command that mutates the TaskList
 * and should automatically save after execution.
 */
public abstract class TaskCommand extends Command {
    /**
     * Constructs a TaskCommand, which is a Command that saves tasks after execution.
     *
     * @param pattern The regex pattern that identifies the command.
     * @param input The raw user input string for this command.
     */
    protected TaskCommand(String pattern, String input) {
        super(pattern, input);
    }

    /**
     * Saves the current TaskList to storage, displaying an error message if saving fails.
     *
     * @param ui The user interface to interact with.
     * @param taskList The list of tasks to save.
     */
    private void safeSave(Ui ui, TaskList taskList) {
        try {
            Storage.save(taskList);
        } catch (IOException e) {
            ui.showMessage("Unable to save your tasks. Changes may be lost.");
        }
    }

    @Override
    public void run(Ui ui, TaskList taskList) throws ApolloException {
        super.run(ui, taskList);
        safeSave(ui, taskList);
    }
}
