package apollo;

import javafx.application.Application;

/**
 * This class serves as the entry point for the JavaFX application and
 * launches the Apollo chatbot application.
 */
public class Launcher {
    /**
     * Starts the Apollo chatbot program using JavaFX.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Apollo.class, args);
    }
}
