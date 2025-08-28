public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    };

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
