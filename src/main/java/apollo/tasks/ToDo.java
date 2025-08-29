package apollo.tasks;

/**
 * Represents a simple task without any date or time.
 * Inherits common functionality from Task.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
