public class Ui {
    public static void showMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    public static void greet() {
        showMessage("Hello! I'm Apollo\nWhat can I do for you?");
    }

    public static void exit() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public static void add(TaskList taskList, String task) {
        taskList.addTask(task);
        showMessage("Added: " + task);
    }

    public static void list(TaskList taskList) {
        showMessage(taskList.toString());
    }
}
