package apollo.commands;

import java.util.Stack;

import apollo.exception.ApolloException;

/**
 * A utility class that maintains a stack of executed commands
 * to support undo functionality in the Apollo chatbot.
 */
public class CommandStack {
    private static final Stack<Command> stack = new Stack<>();

    /**
     * Pushes a command onto the stack.
     *
     * @param cmd the command to push
     */
    public static void push(Command cmd) {
        stack.push(cmd);
    }

    /**
     * Attempts to undo the most recent command in the stack.
     * Skips commands that do not support undo until a valid one is found.
     *
     * @throws ApolloException if no undoable command exists
     */
    public static void undo() throws ApolloException {
        while (!stack.isEmpty()) {
            try {
                stack.pop().undo();
                return;
            } catch (UnsupportedOperationException e) {
                // skip and continue
            }
        }

        throw new ApolloException.NothingToUndoException();
    }
}
