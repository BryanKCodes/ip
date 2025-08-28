import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

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
