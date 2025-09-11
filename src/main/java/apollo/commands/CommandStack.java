package apollo.commands;

import java.util.Stack;

import apollo.exception.ApolloException;

public class CommandStack {
    private final static Stack<Command> stack = new Stack<>();

    public static void push(Command cmd) {
        stack.push(cmd);
    }

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
