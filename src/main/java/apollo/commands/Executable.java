package apollo.commands;

import apollo.exception.ApolloException;

/**
 * Represents an action or operation that can be executed.
 */
@FunctionalInterface
public interface Executable {
    /**
     * Executes the action defined by this instance.
     *
     * @throws ApolloException if execution fails
     */
    void execute() throws ApolloException;
}
