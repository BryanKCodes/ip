package apollo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Stores a due date and inherits common functionality from Task.
 */
public class Deadline extends Task {
    private LocalDate by; // due date of the task

    /**
     * Creates a new Deadline task with the given description and due date.
     *
     * @param description Description of the task.
     * @param by Due date in the format "YYYY-MM-DD".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
