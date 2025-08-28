package apollo.tasks;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ToDoTest {

    private ToDo todo;

    @Before
    public void setup() {
        todo = new ToDo("Buy milk");
    }

    @Test
    public void testToStringAndSaveFormat() {
        assertEquals("[T][ ] Buy milk", todo.toString());
        assertEquals("T | 0 | Buy milk", todo.toSaveFormat());
        todo.markAsDone();
        assertEquals("[T][X] Buy milk", todo.toString());
        assertEquals("T | 1 | Buy milk", todo.toSaveFormat());
    }
}
