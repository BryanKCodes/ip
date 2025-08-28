package apollo.tasks;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTest {

    private Event event;

    @Before
    public void setup() {
        event = new Event("Conference", "2025-09-05", "2025-09-07");
    }

    @Test
    public void testToStringAndSaveFormat() {
        String expectedString = "[E][ ] Conference (from: " +
                LocalDate.parse("2025-09-05").format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " +
                LocalDate.parse("2025-09-07").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(expectedString, event.toString());
        assertEquals("E | 0 | Conference | 2025-09-05 | 2025-09-07", event.toSaveFormat());

        event.markAsDone();
        String doneString = "[E][X] Conference (from: " +
                LocalDate.parse("2025-09-05").format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " +
                LocalDate.parse("2025-09-07").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(doneString, event.toString());
        assertEquals("E | 1 | Conference | 2025-09-05 | 2025-09-07", event.toSaveFormat());
    }
}
