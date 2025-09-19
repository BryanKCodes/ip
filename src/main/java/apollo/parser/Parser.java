package apollo.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private static final Map<String, Function<String, Command>> COMMAND_MAP = new HashMap<>();
    private static final String DELIMITER = "\\s+";

    static {
        COMMAND_MAP.put("list", ListCommand::new);
        COMMAND_MAP.put("mark", MarkCommand::new);
        COMMAND_MAP.put("unmark", UnmarkCommand::new);
        COMMAND_MAP.put("delete", DeleteCommand::new);
        COMMAND_MAP.put("todo", ToDoCommand::new);
        COMMAND_MAP.put("deadline", DeadlineCommand::new);
        COMMAND_MAP.put("event", EventCommand::new);
        COMMAND_MAP.put("find", FindCommand::new);
        COMMAND_MAP.put("undo", UndoCommand::new);
        COMMAND_MAP.put("bye", ByeCommand::new);
    }

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
        String[] parts = inputLower.split(DELIMITER, 2);
        String commandWord = parts[0];

        Function<String, Command> factory = COMMAND_MAP.get(commandWord);
        if (factory == null) {
            throw new ApolloException.UnknownCommandException();
        }

        Command command = factory.apply(input);
        command.run(ui, taskList);
        CommandStack.push(command);

        // Program should exit if ByeCommand was executed
        return command instanceof ByeCommand;
    }
}
