package apollo;

import apollo.exception.ApolloException;
import apollo.parser.Parser;
import apollo.ui.Message;
import apollo.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Starts the Apollo chatbot application with an improved, CSS-styled UI.
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
     * Initializes the core interactive UI components and applies CSS styling.
     */
    private void initializeUiComponents() {
        dialogContainer = new VBox();
        dialogContainer.getStyleClass().add("dialog-container");

        scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // Auto-scroll
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar

        userInput = new TextField();
        userInput.setPromptText("Type a message...");

        sendButton = new Button();

        // Create an SVG icon for the send button
        SVGPath sendIcon = new SVGPath();
        sendIcon.setContent("M2 21l21-9L2 3v7l15 2-15 2v7z");
        sendIcon.setFill(Color.WHITE);
        sendIcon.setScaleX(0.7);
        sendIcon.setScaleY(0.7);
        sendIcon.setTranslateX(2);

        sendButton.setGraphic(sendIcon);
    }

    /**
     * Configures the main layout, scene, and primary stage of the application.
     */
    private void configureStageAndLayout() {
        HBox inputArea = new HBox(userInput, sendButton);
        inputArea.getStyleClass().add("input-area");
        HBox.setHgrow(userInput, Priority.ALWAYS);

        // Apply style classes from CSS
        userInput.getStyleClass().add("text-field");
        sendButton.getStyleClass().add("send-button");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(inputArea);

        Scene scene = new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
        // Load the stylesheet
        String cssPath = this.getClass().getResource("/css/styles.css").toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setScene(scene);
        stage.setTitle("Apollo");
        stage.setMinWidth(350);
        stage.setMinHeight(500);
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
                boolean shouldExit = parser.handle(input);
                if (shouldExit) {
                    // Add a delay before closing for a smoother exit
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> stage.close());
                    delay.play();
                }
            } catch (ApolloException e) {
                ui.showMessage(e.getMessage());
            } finally {
                userInput.clear();
            }
        };

        sendButton.setOnAction(event -> handleUserInputCommand.run());
        userInput.setOnAction(event -> handleUserInputCommand.run());
    }
}
