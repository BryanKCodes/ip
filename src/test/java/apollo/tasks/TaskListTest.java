package apollo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList list;
    private Task t1;
    private Task t2;

    @BeforeEach
    void setUp() {
        list = new TaskList();
        t1 = new ToDo("Buy milk");
        t2 = new Deadline("Return book", "2025-12-01");
    }

    @Test
    void addTask_sizeIncreases() {
        assertEquals(0, list.size());
        list.addTask(t1);
        assertEquals(1, list.size());
        list.addTask(t2);
        assertEquals(2, list.size());
    }

    @Test
    void getTask_returnsCorrectTaskOrNull() {
        list.addTask(t1);
        list.addTask(t2);
        assertEquals(t1, list.getTask(0));
        assertEquals(t2, list.getTask(1));
        assertNull(list.getTask(-1));
        assertNull(list.getTask(2)); // out of bounds
    }

    @Test
    void removeTask_removesCorrectTask() {
        list.addTask(t1);
        list.addTask(t2);
        list.removeTask(0);
        assertEquals(1, list.size());
        assertEquals(t2, list.getTask(0));
    }

    @Test
    void findTasks_returnsMatchingTasks() {
        list.addTask(t1);
        list.addTask(t2);

        var result1 = list.findTasks("milk");
        assertEquals(1, result1.size());
        assertEquals(t1, result1.get(0));

        var result2 = list.findTasks("return");
        assertEquals(1, result2.size());
        assertEquals(t2, result2.get(0));

        var result3 = list.findTasks("BOOK");
        assertEquals(1, result3.size());
        assertEquals(t2, result3.get(0));

        var result4 = list.findTasks("eggs");
        assertEquals(0, result4.size());
    }

    @Test
    void toString_returnsExpectedFormat() {
        list.addTask(t1);
        list.addTask(t2);
        String expected = "1. " + t1 + "\n2. " + t2;
        assertEquals(expected, list.toString());
    }

    @Test
    void size_updatesCorrectly() {
        assertEquals(0, list.size());
        list.addTask(t1);
        assertEquals(1, list.size());
        list.addTask(t2);
        assertEquals(2, list.size());
        list.removeTask(0);
        assertEquals(1, list.size());
    }
}
