package apollo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date.
 * Stores a date range and inherits common functionality from Task.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates a new Event task with the given description, start date, and end date.
     *
     * @param description Description of the event.
     * @param start Start date in the format "YYYY-MM-DD".
     * @param end End date in the format "YYYY-MM-DD".
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
