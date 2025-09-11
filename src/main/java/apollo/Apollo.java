package apollo;

import apollo.exception.ApolloException;
import apollo.parser.Parser;
import apollo.ui.Message;
import apollo.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Starts the Apollo chatbot application.
 * Initializes the parser and user interface, and manages the sending input
 */
public class Apollo extends Application {
    private static final double SCENE_WIDTH = 400;
    private static final double SCENE_HEIGHT = 600;

    private Parser parser;
    private Ui ui;

    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        initializeUiComponents();
        configureStageAndLayout();
        initializeLogic();
        wireEventHandlers();

        this.stage.show();
        ui.greet();
    }

    /**
     * Initializes the core interactive UI components.
     */
    private void initializeUiComponents() {
        dialogContainer = new VBox();
        dialogContainer.setStyle("-fx-background-color: #d8f3dc;");

        scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #d8f3dc;");

        userInput = new TextField();
        sendButton = new Button("Send");
    }

    /**
     * Configures the main layout, scene, and primary stage of the application.
     */
    private void configureStageAndLayout() {
        HBox inputArea = new HBox(10, userInput, sendButton);
        inputArea.setPadding(new Insets(8));
        HBox.setHgrow(userInput, Priority.ALWAYS);
        userInput.setMaxWidth(Double.MAX_VALUE);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(inputArea);
        BorderPane.setMargin(inputArea, new Insets(8));

        Scene scene = new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Apollo Chatbot");
    }

    /**
     * Initializes the business logic components of the application.
     */
    private void initializeLogic() {
        this.ui = new Ui(dialogContainer);
        this.parser = new Parser(ui);
    }

    /**
     * Defines the user input handling logic and attaches it to the UI controls.
     */
    private void wireEventHandlers() {
        Runnable handleUserInputCommand = () -> {
            String input = userInput.getText();
            if (input.isBlank()) {
                return;
            }

            dialogContainer.getChildren().add(new Message(input, true));

            try {
                boolean isRunning = parser.handle(input);
                if (!isRunning) {
                    stage.close();
                }
            } catch (ApolloException e) {
                ui.showMessage(e.getMessage());
            }

            userInput.clear();
            Platform.runLater(() -> scrollPane.setVvalue(1.0));
        };

        sendButton.setOnAction(event -> handleUserInputCommand.run());
        userInput.setOnAction(event -> handleUserInputCommand.run());
    }
}
