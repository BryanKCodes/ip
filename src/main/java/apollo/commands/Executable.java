package apollo.commands;

import apollo.exception.ApolloException;

@FunctionalInterface
public interface Executable{
    void execute() throws ApolloException;
}
