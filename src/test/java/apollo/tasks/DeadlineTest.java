package apollo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    void setup() {
        deadline = new Deadline("Submit report", "2025-09-01");
    }

    @Test
    void testToStringAndSaveFormat() {
        String expectedString = "[D][ ] Submit report (by: "
                + LocalDate.parse("2025-09-01").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(expectedString, deadline.toString());
        assertEquals("D | 0 | Submit report | 2025-09-01", deadline.toSaveFormat());

        deadline.markAsDone();
        String doneString = "[D][X] Submit report (by: "
                + LocalDate.parse("2025-09-01").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(doneString, deadline.toString());
        assertEquals("D | 1 | Submit report | 2025-09-01", deadline.toSaveFormat());
    }
}
