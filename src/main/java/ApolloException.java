public class ApolloException extends Exception {
    public ApolloException(String message) {
        super(message);
    }

    public static class EmptyDescriptionException extends ApolloException {
        public EmptyDescriptionException(String type, String format) {
            super("The description of a " + type + " cannot be empty. Format: " + format);
        }
    }

    public static class InvalidFormatException extends ApolloException {
        public InvalidFormatException(String command, String format) {
            super("Were you trying to " + command + " ? The correct format is: " + format);
        }
    }

    public static class TaskNotFoundException extends ApolloException {
        public TaskNotFoundException(int id) {
            super("Unable to find task " + (id + 1) + ". Please try a different number.");
        }
    }

    public static class InvalidDateFormatException extends ApolloException {
        public InvalidDateFormatException() {
            super("Invalid date format. The correct format is: yyyy-mm-dd");
        }
    }

    public static class UnknownCommandException extends ApolloException {
        public UnknownCommandException() {
            super("Sorry, I do not understand what that means. Please try again.");
        }
    }
}
