package apollo.parser;

import java.io.IOException;

import apollo.commands.ByeCommand;
import apollo.commands.Command;
import apollo.commands.CommandStack;
import apollo.commands.DeadlineCommand;
import apollo.commands.DeleteCommand;
import apollo.commands.EventCommand;
import apollo.commands.FindCommand;
import apollo.commands.ListCommand;
import apollo.commands.MarkCommand;
import apollo.commands.ToDoCommand;
import apollo.commands.UndoCommand;
import apollo.commands.UnmarkCommand;
import apollo.exception.ApolloException;
import apollo.storage.Storage;
import apollo.tasks.TaskList;
import apollo.ui.Ui;

/**
 * Handles parsing and processing of user input commands.
 * Converts textual commands into actions on the TaskList and interacts with ui and Storage.
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Parser and loads the TaskList from storage.
     * If loading fails, initializes an empty TaskList.
     */
    public Parser(Ui ui) {
        assert ui != null : "Ui must not be null";

        this.ui = ui;
        try {
            taskList = Storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    /**
     * Processes the given user input and executes the corresponding action.
     *
     * @param input User input string.
     * @return true if the program should continue, false if it should exit.
     * @throws ApolloException If the input format is invalid or refers to a non-existent task.
     */
    public boolean handle(String input) throws ApolloException {
        String inputLower = input.toLowerCase();
        Command command;
        boolean shouldExit = false;
        boolean shouldUpdateStorage = true;

        if (inputLower.startsWith("list")) {
            command = new ListCommand(input);
            shouldUpdateStorage = false;
        } else if (inputLower.startsWith("mark")) {
            command = new MarkCommand(input);
        } else if (inputLower.startsWith("unmark")) {
            command = new UnmarkCommand(input);
        } else if (inputLower.startsWith("delete")) {
            command = new DeleteCommand(input);
        } else if (inputLower.startsWith("todo")) {
            command = new ToDoCommand(input);
        } else if (inputLower.startsWith("deadline")) {
            command = new DeadlineCommand(input);
        } else if (inputLower.startsWith("event")) {
            command = new EventCommand(input);
        } else if (inputLower.startsWith("find")) {
            command = new FindCommand(input);
            shouldUpdateStorage = false;
        } else if (inputLower.startsWith("undo")) {
            command = new UndoCommand(input);
        } else if (inputLower.startsWith("bye")) {
            command = new ByeCommand(input);
            shouldExit = true;
            shouldUpdateStorage = false;
        } else {
            throw new ApolloException.UnknownCommandException();
        }

        command.run(ui, taskList);
        CommandStack.push(command);
        if (shouldUpdateStorage) {
            safeSave();
        }
        return shouldExit;
    }

    /**
     * Saves the current TaskList to storage, displaying an error message if saving fails.
     */
    private void safeSave() {
        try {
            Storage.save(taskList);
        } catch (IOException e) {
            ui.showMessage("Unable to save your tasks. Changes may be lost.");
        }
    }
}
