package apollo.ui;

import apollo.tasks.Task;
import apollo.tasks.TaskList;
import javafx.scene.layout.VBox;

/**
 * Provides user interface utilities for displaying messages to the user.
 * Handles formatted outputs such as greetings, exits, and task updates.
 */
public class Ui {
    private final VBox dialogContainer;

    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * Displays a message in the dialog container
     *
     * @param message text of the message to display.
     */
    public void showMessage(String message) {
        dialogContainer.getChildren().add(
                new Message(message, false)
        );
    }

    /**
     * Displays the greeting message when the program starts.
     */
    public void greet() {
        showMessage("Hello! I'm Apollo. What can I do for you?");
    }

    /**
     * Displays the exit message when the program ends.
     */
    public void exit() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in the list.
     */
    public void add(Task task, int size) {
        showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                size));
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList Task list to display.
     */
    public void list(TaskList taskList) {
        showMessage("Here are the tasks in your list:\n" + taskList.toString());
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks remaining in the list.
     */
    public void delete(Task task, int size) {
        showMessage(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task,
                size));
    }
}
