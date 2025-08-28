package apollo.tasks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskListTest {

    private TaskList list;
    private Task t1;
    private Task t2;

    @Before
    public void setUp() {
        list = new TaskList();
        t1 = new ToDo("Buy milk");
        t2 = new Deadline("Return book", "2025-12-01");
    }

    @Test
    public void addTask_sizeIncreases() {
        assertEquals(0, list.size());
        list.addTask(t1);
        assertEquals(1, list.size());
        list.addTask(t2);
        assertEquals(2, list.size());
    }

    @Test
    public void getTask_returnsCorrectTaskOrNull() {
        list.addTask(t1);
        list.addTask(t2);
        assertEquals(t1, list.getTask(0));
        assertEquals(t2, list.getTask(1));
        assertNull(list.getTask(-1));
        assertNull(list.getTask(2)); // out of bounds
    }

    @Test
    public void removeTask_removesCorrectTask() {
        list.addTask(t1);
        list.addTask(t2);
        list.removeTask(0);
        assertEquals(1, list.size());
        assertEquals(t2, list.getTask(0));
    }

    @Test
    public void toString_returnsExpectedFormat() {
        list.addTask(t1);
        list.addTask(t2);
        String expected = "1. " + t1 + "\n2. " + t2;
        assertEquals(expected, list.toString());
    }

    @Test
    public void size_updatesCorrectly() {
        assertEquals(0, list.size());
        list.addTask(t1);
        assertEquals(1, list.size());
        list.addTask(t2);
        assertEquals(2, list.size());
        list.removeTask(0);
        assertEquals(1, list.size());
    }
}
