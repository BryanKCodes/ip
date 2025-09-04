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
    @Override
    public void start(Stage stage) {
        VBox dialogContainer = new VBox();
        dialogContainer.setStyle("-fx-background-color: #d8f3dc;");

        ScrollPane scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #d8f3dc;");

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        HBox inputArea = new HBox(10, userInput, sendButton);
        inputArea.setPadding(new Insets(8));

        HBox.setHgrow(userInput, Priority.ALWAYS);
        userInput.setMaxWidth(Double.MAX_VALUE);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(inputArea);
        BorderPane.setMargin(inputArea, new Insets(8));

        Scene scene = new Scene(mainLayout, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Apollo Chatbot");
        stage.show();

        Ui ui = new Ui(dialogContainer);
        Parser parser = new Parser(ui);

        ui.greet();

        Runnable handleSend = () -> {
            String input = userInput.getText();
            if (input.isBlank()) {
                return;
            }

            dialogContainer.getChildren().add(
                    new Message(input, true)
            );

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

        sendButton.setOnAction(event -> handleSend.run());
        userInput.setOnAction(event -> handleSend.run());
    }
}
