package apollo.ui;

import apollo.tasks.Task;
import apollo.tasks.TaskList;

/**
 * Provides user interface utilities for displaying messages to the user.
 * Handles formatted outputs such as greetings, exits, and task updates.
 */
public class Ui {
    /**
     * Displays a message surrounded by borders.
     *
     * @param message Message to display.
     */
    public static void showMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    /**
     * Displays the greeting message when the program starts.
     */
    public static void greet() {
        showMessage("Hello! I'm apollo.Apollo\nWhat can I do for you?");
    }

    /**
     * Displays the exit message when the program ends.
     */
    public static void exit() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in the list.
     */
    public static void add(Task task, int size) {
        showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, size));
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList Task list to display.
     */
    public static void list(TaskList taskList) {
        showMessage("Here are the tasks in your list:\n" + taskList.toString());
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks remaining in the list.
     */
    public static void delete(Task task, int size) {
        showMessage(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task, size));
    }
}
