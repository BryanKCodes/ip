package apollo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

    private Event event;

    @BeforeEach
    void setup() {
        event = new Event("Conference", "2025-09-05", "2025-09-07");
    }

    @Test
    void testToStringAndSaveFormat() {
        String expectedString = "[E][ ] Conference (from: "
                + LocalDate.parse("2025-09-05").format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + LocalDate.parse("2025-09-07").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(expectedString, event.toString());
        assertEquals("E | 0 | Conference | 2025-09-05 | 2025-09-07", event.toSaveFormat());

        event.markAsDone();
        String doneString = "[E][X] Conference (from: "
                + LocalDate.parse("2025-09-05").format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + LocalDate.parse("2025-09-07").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(doneString, event.toString());
        assertEquals("E | 1 | Conference | 2025-09-05 | 2025-09-07", event.toSaveFormat());
    }
}
