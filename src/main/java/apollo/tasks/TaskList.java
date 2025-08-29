package apollo.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, retrieve, and display tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param id Index of the task to remove (0-based).
     */
    public void removeTask(int id) {
        tasks.remove(id);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param id Index of the task to retrieve (0-based).
     * @return Task at the given index, or null if index is out of bounds.
     */
    public Task getTask(int id) {
        if (id < 0 || id >= tasks.size()) {
            return null;
        }
        return tasks.get(id);
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList containing all tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks. Empty list if none found.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
