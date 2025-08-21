public class TaskList {
    private String[] tasks = new String[100];
    private int taskCount = 0;

    public void addTask(String task) {
        tasks[taskCount++] = task;
    }

    @Override
    public String toString() {
        if (taskCount == 0) {
            return "No tasks";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            sb.append(i + 1).append(". ").append(tasks[i]);
            if (i < taskCount - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
