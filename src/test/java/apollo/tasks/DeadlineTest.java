package apollo.tasks;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    private Deadline deadline;

    @Before
    public void setup() {
        deadline = new Deadline("Submit report", "2025-09-01");
    }

    @Test
    public void testToStringAndSaveFormat() {
        String expectedString = "[D][ ] Submit report (by: " +
                LocalDate.parse("2025-09-01").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(expectedString, deadline.toString());
        assertEquals("D | 0 | Submit report | 2025-09-01", deadline.toSaveFormat());

        deadline.markAsDone();
        String doneString = "[D][X] Submit report (by: " +
                LocalDate.parse("2025-09-01").format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(doneString, deadline.toString());
        assertEquals("D | 1 | Submit report | 2025-09-01", deadline.toSaveFormat());
    }
}
