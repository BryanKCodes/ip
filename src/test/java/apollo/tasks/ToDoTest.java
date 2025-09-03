package apollo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    private ToDo todo;

    @BeforeEach
    void setup() {
        todo = new ToDo("Buy milk");
    }

    @Test
    void testToStringAndSaveFormat() {
        assertEquals("[T][ ] Buy milk", todo.toString());
        assertEquals("T | 0 | Buy milk", todo.toSaveFormat());
        todo.markAsDone();
        assertEquals("[T][X] Buy milk", todo.toString());
        assertEquals("T | 1 | Buy milk", todo.toSaveFormat());
    }
}
