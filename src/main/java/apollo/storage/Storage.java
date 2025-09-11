package apollo.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import apollo.tasks.Deadline;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import apollo.tasks.ToDo;

/**
 * Handles loading and saving of TaskList to persistent storage.
 * Reads and writes tasks from/to a text file in a predefined format.
 */
public class Storage {
    private static final String FILE_PATH = "./data/apollo.txt";

    /**
     * Loads the TaskList from storage.
     * If the file does not exist, creates a new file and returns an empty TaskList.
     *
     * @return The TaskList loaded from storage.
     * @throws IOException If an I/O error occurs during reading or file creation.
     */
    public static TaskList load() throws IOException {
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

    /**
     * Saves the given TaskList to storage.
     * Creates parent directories if they do not exist.
     *
     * @param tasks TaskList to be saved.
     * @throws IOException If an I/O error occurs during writing.
     */
    public static void save(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList to be saved cannot be null";

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

    /**
     * Decodes a line from the save file into a Task object.
     * Supports ToDo, Deadline, and Event task types.
     *
     * @param line Line read from the save file.
     * @return Task object corresponding to the line.
     * @throws IllegalStateException If the task type in the line is unrecognized.
     */
    private static Task decodeTaskFromLine(String line) {
        assert line != null && !line.isEmpty() : "Line from save file cannot be null or empty";

        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = !parts[1].equals("0");

        Task task = switch (type) {
        case "T" -> {
            assert parts.length == 3 : "ToDo should have 3 parts";
            yield new ToDo(parts[2]);
        }
        case "D" -> {
            assert parts.length == 4 : "Deadline should have 4 parts";
            yield new Deadline(parts[2], parts[3]);
        }
        case "E" -> {
            assert parts.length == 5 : "Event should have 5 parts";
            yield new Event(parts[2], parts[3], parts[4]);
        }
        default -> throw new IllegalStateException("Unexpected task type: " + type);
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
