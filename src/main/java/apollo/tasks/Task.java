package apollo.tasks;

/**
 * Represents a task with a description and completion status.
 * Provides methods to mark the task as done or undone, and
 * to format the task for saving or displaying.
 */
public class Task {
    private String description;
    private boolean isDone; // completion status of task

    /**
     * Creates a new Task with the given description.
     * The task is initially not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     * "X" if done, " " if not done.
     *
     * @return Status icon as a string.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of this task for saving to file.
     *
     * @return Formatted string for saving.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    };

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
