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

    public static void add(Task task, int size) {
        showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, size));
    }

    public static void list(TaskList taskList) {
        showMessage("Here are the tasks in your list:\n" + taskList.toString());
    }

    public static void delete(Task task, int size) {
        showMessage(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task, size));
    }
}
