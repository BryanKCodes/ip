import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String FILE_PATH = "./data/apollo.txt";

    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            Task task = decodeTaskFromLine(line);
            tasks.addTask(task);
        }

        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                bw.write(task.toSaveFormat());
                bw.newLine();
            }
        }
    }

    private static Task decodeTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = !parts[1].equals("0");

        Task task = switch (type) {
            case "T" -> new ToDo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> throw new IllegalStateException("Unexpected task type: " + type);
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
