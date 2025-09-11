package apollo.exception;

/**
 * Represents exceptions specific to the Apollo application.
 * Provides different types of exceptions for invalid user input or task operations.
 */
public class ApolloException extends Exception {
    /**
     * Constructs an ApolloException with the specified message.
     *
     * @param message The exception message.
     */
    public ApolloException(String message) {
        super(message);
    }

    /**
     * Exception thrown when a user provides an empty description for a task.
     */
    public static class EmptyDescriptionException extends ApolloException {
        /**
         * Constructs an EmptyDescriptionException with details about the command type and format.
         *
         * @param type The type of task (e.g., "todo", "deadline").
         * @param format The expected command format.
         */
        public EmptyDescriptionException(String type, String format) {
            super("The description of a " + type + " cannot be empty. Format: " + format);
        }
    }

    /**
     * Exception thrown when a user enters a command with an invalid format.
     */
    public static class InvalidFormatException extends ApolloException {
        /**
         * Constructs an InvalidFormatException with details about the command and expected format.
         *
         * @param command The command the user tried to execute.
         * @param format The correct format for the command.
         */
        public InvalidFormatException(String command, String format) {
            super("Were you trying to " + command + "? The correct format is: " + format);
        }
    }

    /**
     * Exception thrown when a task with a specified index cannot be found.
     */
    public static class TaskNotFoundException extends ApolloException {
        /**
         * Constructs a TaskNotFoundException for the given task index.
         *
         * @param id The 0-based index of the task that was not found.
         */
        public TaskNotFoundException(int id) {
            super("Unable to find task " + (id + 1) + ". Please try a different number.");
        }
    }

    /**
     * Exception thrown when a date is provided in an invalid format.
     */
    public static class InvalidDateFormatException extends ApolloException {
        /**
         * Constructs an InvalidDateFormatException with a default message.
         */
        public InvalidDateFormatException() {
            super("Invalid date format. The correct format is: yyyy-mm-dd");
        }
    }

    /**
     * Exception thrown when there are no commands available to undo.
     */
    public static class NothingToUndoException extends ApolloException {
        /**
         * Constructs a NothingToUndoException with a default message.
         */
        public NothingToUndoException() {
            super("There is nothing left to undo");
        }
    }

    /**
     * Exception thrown when a user enters an unknown or unsupported command.
     */
    public static class UnknownCommandException extends ApolloException {
        /**
         * Constructs an UnknownCommandException with a default message.
         */
        public UnknownCommandException() {
            super("Sorry, I do not understand what that means. Please try again.");
        }
    }
}
